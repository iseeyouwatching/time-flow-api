package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.CreateLessonForAFewWeeksDtoMapper;
import ru.hits.timeflowapi.mapper.LessonMapper;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonForAFewWeeksDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherTimetableDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.repository.*;
import ru.hits.timeflowapi.service.helpingservices.CheckClassroomAndTeacherAndTimeslotAccessibility;
import ru.hits.timeflowapi.service.helpingservices.CheckCreateLessonDtoValidity;
import ru.hits.timeflowapi.service.helpingservices.VerificationOfDates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final TimeslotRepository timeslotRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final CheckClassroomAndTeacherAndTimeslotAccessibility checkClassroomAndTeacherAndTimeslotAccessibility;
    private final CheckCreateLessonDtoValidity checkCreateLessonDtoValidity;
    private final VerificationOfDates verificationOfDates;
    private final CreateLessonForAFewWeeksDtoMapper createLessonForAFewWeeksDtoMapper;
    private final LessonMapper lessonMapper;

    public StudentGroupTimetableDto getWeekLessonsByGroupId(UUID groupId, LocalDate startDate, LocalDate endDate) {

        verificationOfDates.checkDates(startDate, endDate);

        StudentGroupEntity studentGroup = studentGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new NotFoundException("Студенческой группы с таким ID " + groupId + " не существует"));

        List<LessonEntity> lessons = lessonRepository
                .findByStudentGroupAndDateIsBetweenOrderByDate(studentGroup, startDate, endDate);

        return new StudentGroupTimetableDto(
                new StudentGroupBasicDto(studentGroup), lessonMapper.lessonListToDtoList(lessons)
        );
    }

    public TeacherTimetableDto getWeekLessonsByTeacherId(UUID teacherId, LocalDate startDate, LocalDate endDate) {

        verificationOfDates.checkDates(startDate, endDate);

        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new NotFoundException("Преподавателя с таким ID " + teacherId + " не существует"));

        List<LessonEntity> lessons = lessonRepository
                .findByTeacherAndDateIsBetweenOrderByDate(teacher, startDate, endDate);

        return new TeacherTimetableDto(new TeacherDto(teacher), lessonMapper.lessonListToDtoList(lessons));
    }

    public ClassroomTimetableDto getWeekLessonsByClassroomId(UUID classroomId, LocalDate startDate, LocalDate endDate) {

        verificationOfDates.checkDates(startDate, endDate);

        ClassroomEntity classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() ->
                        new NotFoundException("Аудитории с таким ID " + classroomId + " не существует"));

        List<LessonEntity> lessons = lessonRepository
                .findByClassroomAndDateIsBetweenOrderByDate(classroom, startDate, endDate);

        return new ClassroomTimetableDto(new ClassroomDto(classroom), lessonMapper.lessonListToDtoList(lessons));
    }

    public LessonDto getLessonById(UUID id) {

        LessonEntity lesson = lessonRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пары с таким ID " + id + " не существует"));

        return new LessonDto(lesson);
    }

    public LessonDto addLesson(CreateLessonDto createLessonDto) {
        LessonEntity lesson = new LessonEntity();

        LessonEntity lessonWithValidId = checkCreateLessonDtoValidity.checkIdValidity(createLessonDto);
        checkClassroomAndTeacherAndTimeslotAccessibility.checkAccessibility(
                createLessonDto.getTimeslotId(),
                createLessonDto.getTeacherId(),
                createLessonDto.getClassroomId(),
                createLessonDto.getStudentGroupId(),
                createLessonDto.getDate()
        );

        return new LessonDto(setLesson(lesson, lessonWithValidId));
    }

    public void deleteLesson(UUID id) {
        if (lessonRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Пары с таким ID " + id + " не существует");
        }
        lessonRepository.deleteById(id);
    }

    public void deleteAllLessonsByWeek(LocalDate startDate, LocalDate endDate) {
        verificationOfDates.checkDates(startDate, endDate);
        lessonRepository.deleteByDateIsBetween(startDate, endDate);
    }

    public LessonDto updateLesson(UUID id, CreateLessonDto updatedLessonDto) {
        LessonEntity lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пары с таким ID " + id + " не существует"));

        LessonEntity lessonWithValidId = checkCreateLessonDtoValidity.checkIdValidity(updatedLessonDto);
        checkClassroomAndTeacherAndTimeslotAccessibility.checkAccessibility(
                updatedLessonDto.getTimeslotId(),
                updatedLessonDto.getTeacherId(),
                updatedLessonDto.getClassroomId(),
                updatedLessonDto.getStudentGroupId(),
                updatedLessonDto.getDate()
        );

        return new LessonDto(setLesson(lesson, lessonWithValidId));
    }

    /**
     * Метод для добавления новой пары в бд.
     *
     * @param lesson            LessonEntity, которую меняют/добавляют в бд.
     * @param lessonWithValidId LessonEntity с валидными ID.
     * @return LessonEntity, заполненная новыми данными.
     */
    private LessonEntity setLesson(LessonEntity lesson, LessonEntity lessonWithValidId) {
        lesson.setStudentGroup(lessonWithValidId.getStudentGroup());
        lesson.setSubject(lessonWithValidId.getSubject());
        lesson.setTeacher(lessonWithValidId.getTeacher());
        lesson.setClassroom(lessonWithValidId.getClassroom());
        lesson.setTimeslot(lessonWithValidId.getTimeslot());
        lesson.setDate(lessonWithValidId.getDate());
        lesson.setLessonType(lessonWithValidId.getLessonType());

        lessonRepository.save(lesson);

        return lesson;
    }

    /**
     * Метод для добавления пары сразу на какое-то количество недель.
     *
     * @param createLessonForAFewWeeksDto DTO для создания пары сразу на какое-то количество недель.
     * @return список только что добавленных пар.
     */
    public List<LessonDto> addLessonForAFewWeeks(CreateLessonForAFewWeeksDto createLessonForAFewWeeksDto) {

        CreateLessonDto createLessonDto = createLessonForAFewWeeksDtoMapper.
                createLessonForAFewWeeksDtoToCreateLessonDto(createLessonForAFewWeeksDto);

        LessonEntity lessonWithValidId = checkCreateLessonDtoValidity.checkIdValidity(createLessonDto);

        List<LessonDto> lessonDtos = new ArrayList<>();

        for (long i = 0; i < createLessonForAFewWeeksDto.getNumberOfWeeks(); i++) {
            checkClassroomAndTeacherAndTimeslotAccessibility.checkAccessibility(
                    createLessonDto.getTimeslotId(),
                    createLessonDto.getTeacherId(),
                    createLessonDto.getClassroomId(),
                    createLessonDto.getStudentGroupId(),
                    createLessonDto.getDate().plusDays(i*7)
            );

            lessonWithValidId.setDate(createLessonDto.getDate().plusDays(i*7));
            lessonWithValidId.setLessonType(createLessonDto.getLessonType());

            LessonEntity lesson = new LessonEntity();

            lessonDtos.add(new LessonDto(setLesson(lesson, lessonWithValidId)));
        }

        return lessonDtos;

    }
}
