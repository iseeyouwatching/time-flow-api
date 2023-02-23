package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.dto.*;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonsDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final TimeslotRepository timeslotRepository;
    private final ClassroomRepository classroomRepository;
    private final WeekRepository weekRepository;
    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupTimetableDto getLessonsByGroupId(UUID id) {

        StudentGroupEntity studentGroup = studentGroupRepository.findById(id).orElse(null);

        if (studentGroup == null) {
            throw new NotFoundException("Студенческой группы с таким ID " + id + " не существует");
        }

        StudentGroupBasicDto studentGroupBasicDto = new StudentGroupBasicDto(studentGroup);

        List<LessonEntity> lessons = lessonRepository.findByStudentGroup(studentGroup);

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
                    lesson.getLessonType()));
        }

        return new StudentGroupTimetableDto(studentGroupBasicDto, lessonDtos);

    }

    public TeacherTimetableDto getLessonsByTeacherId(UUID id) {

        TeacherEntity teacher = teacherRepository.findById(id).orElse(null);

        if (teacher == null) {
            throw new NotFoundException("Преподавателя с таким ID " + id + " не существует");
        }

        TeacherDto teacherDto = new TeacherDto(teacher);

        List<LessonEntity> lessons = lessonRepository.findByTeacher(teacher);

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
                    lesson.getLessonType()));
        }

        return new TeacherTimetableDto(teacherDto, lessonDtos);

    }

    public ClassroomTimetableDto getLessonsByClassroomId(UUID id) {

        ClassroomEntity classroom = classroomRepository.findById(id).orElse(null);

        if (classroom == null) {
            throw new NotFoundException("Аудитории с таким ID " + id + " не существует");
        }

        ClassroomDto classroomDto = new ClassroomDto(classroom);

        List<LessonEntity> lessons = lessonRepository.findByClassroom(classroom);

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
                    lesson.getLessonType()));
        }

        return new ClassroomTimetableDto(classroomDto, lessonDtos);

    }

    public LessonDto getLessonById(UUID id) {

        return new LessonDto(Objects.requireNonNull(lessonRepository.findById(id).orElse(null)));

    }

    public LessonDto addLesson(CreateLessonDto createLessonDto) {

        LessonEntity lesson = new LessonEntity();

        lesson.setStudentGroup(studentGroupRepository.findById(createLessonDto.getStudentGroupId()).orElse(null));
        lesson.setSubject(subjectRepository.findById(createLessonDto.getSubjectId()).orElse(null));
        lesson.setTeacher(teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null));
        lesson.setClassroom(classroomRepository.findById(createLessonDto.getClassroomId()).orElse(null));
        lesson.setTimeslot(timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null));
        lesson.setWeek(weekRepository.findById(createLessonDto.getWeekId()).orElse(null));
        lesson.setLessonType(LessonType.values()[createLessonDto.getLessonType()]);

        lessonRepository.save(lesson);

        return new LessonDto(lesson);

    }

    public LessonsDto getAllLessons() {

        List<LessonDto> lessonDtos = new ArrayList<>();

        List<LessonEntity> lessons = lessonRepository.findAll();

        for (LessonEntity lesson: lessons) {
            lessonDtos.add(new LessonDto(
                    lesson.getId(),
                    new StudentGroupBasicDto(lesson.getStudentGroup()),
                    new SubjectDto(lesson.getSubject()),
                    new TeacherDto(lesson.getTeacher()),
                    new ClassroomDto(lesson.getClassroom()),
                    new TimeslotDto(lesson.getTimeslot()),
                    new WeekDto(lesson.getWeek()),
                    lesson.getLessonType()));
        }

        return new LessonsDto(lessonDtos);

    }
}
