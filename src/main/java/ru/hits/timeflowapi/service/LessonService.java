package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.WeekDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.day.DayDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherTimetableDto;
import ru.hits.timeflowapi.model.entity.*;
import ru.hits.timeflowapi.model.enumeration.LessonType;
import ru.hits.timeflowapi.repository.*;

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
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;

    public StudentGroupTimetableDto getWeekLessonsByGroupId(UUID groupId, Integer page) {

        StudentGroupEntity studentGroup = studentGroupRepository.findById(groupId).orElse(null);

        if (studentGroup == null) {
            throw new NotFoundException("Студенческой группы с таким ID " + groupId + " не существует");
        }

        WeekEntity week = weekRepository.findBySequenceNumber(page);

        if (week == null) {
            throw new NotFoundException("Недели с таким порядковым номером " + page + " не существует");
        }

        List<LessonEntity> lessons = lessonRepository.findByStudentGroupAndWeek(studentGroup, week);

        List<LessonDto> lessonDtos = new ArrayList<>();

        for (LessonEntity lesson: lessons) {
            lessonDtos.add(new LessonDto(
                    lesson.getId(),
                    new StudentGroupBasicDto(lesson.getStudentGroup()),
                    new SubjectDto(lesson.getSubject()),
                    new TeacherDto(lesson.getTeacher()),
                    new ClassroomDto(lesson.getClassroom()),
                    new TimeslotDto(lesson.getTimeslot()),
                    new WeekDto(lesson.getWeek()),
                    new DayDto(lesson.getDay()),
                    lesson.getLessonType())
            );
        }

        return new StudentGroupTimetableDto(new StudentGroupBasicDto(studentGroup), week.getId(), lessonDtos);

    }

    public TeacherTimetableDto getWeekLessonsByTeacherId(UUID teacherId, Integer page) {

        TeacherEntity teacher = teacherRepository.findById(teacherId).orElse(null);

        if (teacher == null) {
            throw new NotFoundException("Преподавателя с таким ID " + teacherId + " не существует");
        }

        WeekEntity week = weekRepository.findBySequenceNumber(page);

        if (week == null) {
            throw new NotFoundException("Недели с таким порядковым номером " + page + " не существует");
        }

        List<LessonEntity> lessons = lessonRepository.findByTeacherAndWeek(teacher, week);

        List<LessonDto> lessonDtos = new ArrayList<>();

        for (LessonEntity lesson: lessons) {
            lessonDtos.add(new LessonDto(
                    lesson.getId(),
                    new StudentGroupBasicDto(lesson.getStudentGroup()),
                    new SubjectDto(lesson.getSubject()),
                    new TeacherDto(lesson.getTeacher()),
                    new ClassroomDto(lesson.getClassroom()),
                    new TimeslotDto(lesson.getTimeslot()),
                    new WeekDto(lesson.getWeek()),
                    new DayDto(lesson.getDay()),
                    lesson.getLessonType())
            );
        }

        return new TeacherTimetableDto(new TeacherDto(teacher), week.getId(), lessonDtos);

    }

    public ClassroomTimetableDto getWeekLessonsByClassroomId(UUID classroomId, Integer page) {

        ClassroomEntity classroom = classroomRepository.findById(classroomId).orElse(null);

        if (classroom == null) {
            throw new NotFoundException("Аудитории с таким ID " + classroomId + " не существует");
        }

        WeekEntity week = weekRepository.findBySequenceNumber(page);

        if (week == null) {
            throw new NotFoundException("Недели с таким порядковым номером " + page + " не существует");
        }

        List<LessonEntity> lessons = lessonRepository.findByClassroomAndWeek(classroom, week);

        List<LessonDto> lessonDtos = new ArrayList<>();

        for (LessonEntity lesson: lessons) {
            lessonDtos.add(new LessonDto(
                    lesson.getId(),
                    new StudentGroupBasicDto(lesson.getStudentGroup()),
                    new SubjectDto(lesson.getSubject()),
                    new TeacherDto(lesson.getTeacher()),
                    new ClassroomDto(lesson.getClassroom()),
                    new TimeslotDto(lesson.getTimeslot()),
                    new WeekDto(lesson.getWeek()),
                    new DayDto(lesson.getDay()),
                    lesson.getLessonType())
            );
        }

        return new ClassroomTimetableDto(new ClassroomDto(classroom), week.getId(), lessonDtos);

    }

    public LessonDto getLessonById(UUID id) {

        LessonEntity lesson = lessonRepository.findById(id).orElse(null);

        if (lesson == null) {
            throw new NotFoundException("Пары с таким ID " + id + " не существует");
        }

        return new LessonDto(lesson);

    }

    public LessonDto addLesson(CreateLessonDto createLessonDto) {

        DayEntity day = dayRepository.findById(createLessonDto.getDayId()).orElse(null);
        WeekEntity week = weekRepository.findById(createLessonDto.getWeekId()).orElse(null);

        if (day == null) {
            throw new NotFoundException("Дня с таким ID " + createLessonDto.getDayId() + " не существует");
        }

        if (week == null) {
            throw new NotFoundException("Недели с таким ID " + createLessonDto.getWeekId() + " не существует");
        }

        if (day.getWeek().getId().compareTo(week.getId()) != 0) {
            throw new ConflictException("День с ID " + createLessonDto.getDayId() + " не соответствует неделе c ID " + createLessonDto.getWeekId());
        }

        LessonEntity lesson = new LessonEntity();

        lesson.setStudentGroup(studentGroupRepository.findById(createLessonDto.getStudentGroupId()).orElse(null));
        lesson.setSubject(subjectRepository.findById(createLessonDto.getSubjectId()).orElse(null));
        lesson.setTeacher(teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null));
        lesson.setClassroom(classroomRepository.findById(createLessonDto.getClassroomId()).orElse(null));
        lesson.setTimeslot(timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null));
        lesson.setWeek(weekRepository.findById(createLessonDto.getWeekId()).orElse(null));
        lesson.setDay(dayRepository.findById(createLessonDto.getDayId()).orElse(null));
        lesson.setLessonType(LessonType.valueOf(createLessonDto.getLessonType()));

        lessonRepository.save(lesson);

        return new LessonDto(lesson);

    }

    public void deleteLesson(UUID id) {

        if (lessonRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Пары с таким ID " + id + " не существует");
        }

        lessonRepository.deleteById(id);

    }

    public LessonDto updateLesson(UUID id, CreateLessonDto updatedLessonDto) {

        LessonEntity lesson = lessonRepository.findById(id).orElse(null);

        if (lesson == null) {
            throw new NotFoundException("Пары с таким ID " + id + " не существует");
        }

        DayEntity day = dayRepository.findById(updatedLessonDto.getDayId()).orElse(null);
        WeekEntity week = weekRepository.findById(updatedLessonDto.getWeekId()).orElse(null);

        if (day == null) {
            throw new NotFoundException("Дня с таким ID " + updatedLessonDto.getDayId() + " не существует");
        }

        if (week == null) {
            throw new NotFoundException("Недели с таким ID " + updatedLessonDto.getWeekId() + " не существует");
        }

        if (day.getWeek().getId().compareTo(week.getId()) != 0) {
            throw new ConflictException("День с ID " + day.getId() + " не соответствует неделе c ID " + updatedLessonDto.getWeekId());
        }

        lesson.setStudentGroup(studentGroupRepository.findById(updatedLessonDto.getStudentGroupId()).orElse(null));
        lesson.setSubject(subjectRepository.findById(updatedLessonDto.getSubjectId()).orElse(null));
        lesson.setTeacher(teacherRepository.findById(updatedLessonDto.getTeacherId()).orElse(null));
        lesson.setClassroom(classroomRepository.findById(updatedLessonDto.getClassroomId()).orElse(null));
        lesson.setTimeslot(timeslotRepository.findById(updatedLessonDto.getTimeslotId()).orElse(null));
        lesson.setWeek(weekRepository.findById(updatedLessonDto.getWeekId()).orElse(null));
        lesson.setDay(dayRepository.findById(updatedLessonDto.getDayId()).orElse(null));
        lesson.setLessonType(LessonType.valueOf(updatedLessonDto.getLessonType()));

        lessonRepository.save(lesson);

        return new LessonDto(lesson);

    }

}