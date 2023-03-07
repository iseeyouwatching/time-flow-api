package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.LessonMapper;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherTimetableDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.model.enumeration.LessonType;
import ru.hits.timeflowapi.repository.*;
import ru.hits.timeflowapi.service.helpingservices.CheckClassroomAndTeacherAndTimeslotAccessibility;
import ru.hits.timeflowapi.service.helpingservices.CheckCreateLessonDtoValidity;
import ru.hits.timeflowapi.service.helpingservices.VerificationOfDates;

import java.time.LocalDate;
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

        return new LessonDto(setLesson(lesson, createLessonDto));
    }

    public void deleteLesson(UUID id) {

        if (lessonRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Пары с таким ID " + id + " не существует");
        }
        lessonRepository.deleteById(id);
    }

    /**
     * Метод, предназначенный для удаления всех пар конкретной группы студентов, которые
     * проходят между датой начала учебной недели и датой конца учебной недели.
     *
     * @param groupId   уникальный идентификатор группы студентов, пары которой будут удалены.
     * @param startDate дата начала учебной недели.
     * @param endDate   дата конца учебной недели.
     * @throws NotFoundException если группы студентов с указанными уникальными идентификатором не существует.
     * @throws IllegalArgumentException если дата начала учебной недели позже даты конца или даты не образуют
     * учебную неделю.
     */
    public void deleteAllLessonsByWeek(UUID groupId, LocalDate startDate, LocalDate endDate) {
        verificationOfDates.checkDates(startDate, endDate);

        StudentGroupEntity studentGroup = studentGroupRepository.findById(groupId)
                .orElseThrow(null);

        if (studentGroup == null) {
            throw new NotFoundException("Группы студентов с таким ID " + groupId + " не существует");
        }

        List<LessonEntity> lessonEntities = lessonRepository.findByStudentGroupAndDateBetween(
                studentGroup,
                startDate,
                endDate
        );

        lessonRepository.deleteAll(lessonEntities);
    }

    public LessonDto updateLesson(UUID id, CreateLessonDto updatedLessonDto) {

        LessonEntity lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пары с таким ID " + id + " не существует"));

        return new LessonDto(setLesson(lesson, updatedLessonDto));
    }

    /**
     * Метод для добавления новой пары в бд.
     *
     * @param lesson          LessonEntity, которую меняют/добавляют в бд.
     * @param createLessonDto дто с данными о паре.
     * @return LessonEntity, заполненная новыми данными.
     */
    private LessonEntity setLesson(LessonEntity lesson, CreateLessonDto createLessonDto) {

        checkCreateLessonDtoValidity.checkIdValidity(createLessonDto);
        checkClassroomAndTeacherAndTimeslotAccessibility.checkAccessibility(createLessonDto);

        lesson.setStudentGroup(studentGroupRepository.findById(createLessonDto.getStudentGroupId()).orElse(null));
        lesson.setSubject(subjectRepository.findById(createLessonDto.getSubjectId()).orElse(null));
        lesson.setTeacher(teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null));
        lesson.setClassroom(classroomRepository.findById(createLessonDto.getClassroomId()).orElse(null));
        lesson.setTimeslot(timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null));
        lesson.setDate(createLessonDto.getDate());
        lesson.setLessonType(LessonType.valueOf(createLessonDto.getLessonType()));
        lessonRepository.save(lesson);
        return lesson;
    }
}