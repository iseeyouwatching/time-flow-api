package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.dto.*;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
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
    private final WeekRepository weekRepository;
    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupTimetableDto getLessonsByGroup(UUID id) {

        StudentGroupEntity studentGroup = studentGroupRepository.findById(id).orElse(null);

        if (studentGroup == null) {
            throw new NotFoundException("Студенческой группы с таким ID " + id + " не существует");
        }

        StudentGroupBasicDto studentGroupBasicDto = new StudentGroupBasicDto(studentGroup);

        List<LessonEntity> lessonEntities = lessonRepository.findByStudentGroup(studentGroup);

        List<LessonDto> lessonDtos = new ArrayList<>();

        for (LessonEntity lesson: lessonEntities) {
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

    public void addLesson(CreateLessonDto createLessonDto) {

        LessonEntity lesson = new LessonEntity();

        lesson.setStudentGroup(studentGroupRepository.findById(createLessonDto.getStudentGroupId()).orElse(null));
        lesson.setSubject(subjectRepository.findById(createLessonDto.getSubjectId()).orElse(null));
        lesson.setTeacher(teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null));
        lesson.setClassroom(classroomRepository.findById(createLessonDto.getClassroomId()).orElse(null));
        lesson.setTimeslot(timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null));
        lesson.setWeek(weekRepository.findById(createLessonDto.getWeekId()).orElse(null));
        lesson.setLessonType(LessonType.values()[createLessonDto.getLessonType()]);

        lessonRepository.save(lesson);

    }
}
