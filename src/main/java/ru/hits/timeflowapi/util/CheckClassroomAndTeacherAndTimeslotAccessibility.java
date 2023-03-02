package ru.hits.timeflowapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.repository.*;

@Component
@RequiredArgsConstructor
public class CheckClassroomAndTeacherAndTimeslotAccessibility {

    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentGroupRepository studentGroupRepository;

    public void checkTeacherIsFree(CreateLessonDto createLessonDto) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndTeacherAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null),
                teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null),
                createLessonDto.getDate());

        if (lesson != null) {
            throw new ConflictException("Преподаватель с ID " + createLessonDto.getTeacherId() + " в это время занят");
        }
    }

    public void checkClassroomIsFree(CreateLessonDto createLessonDto) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndClassroomAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null),
                classroomRepository.findById(createLessonDto.getClassroomId()).orElse(null),
                createLessonDto.getDate());

        if (lesson != null) {
            throw new ConflictException("Аудитория с ID " + createLessonDto.getClassroomId() + " в это время занята");
        }
    }

    public void checkTimeslotIsFree(CreateLessonDto createLessonDto) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndStudentGroupAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null),
                studentGroupRepository.findById(createLessonDto.getStudentGroupId()).orElse(null),
                createLessonDto.getDate());

        if (lesson != null) {
            throw new ConflictException("Таймслот с ID " + createLessonDto.getClassroomId() + " на дату " + createLessonDto.getDate() + " занят у группы с ID " + createLessonDto.getStudentGroupId());
        }
    }

    public void checkAccessibility(CreateLessonDto createLessonDto) {
        checkTeacherIsFree(createLessonDto);
        checkClassroomIsFree(createLessonDto);
        checkTimeslotIsFree(createLessonDto);
    }

}
