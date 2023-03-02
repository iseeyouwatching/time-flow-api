package ru.hits.timeflowapi.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.repository.*;

import java.util.Optional;

/**
 * Сервис, предназначенный для проверки доступности
 * аудитории/преподавателя/таймслота в конкретное время.
 */
@Service
@RequiredArgsConstructor
public class CheckClassroomAndTeacherAndTimeslotAccessibility {

    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentGroupRepository studentGroupRepository;

    public void checkTeacherIsFree(CreateLessonDto createLessonDto) {
        Optional.ofNullable(lessonRepository.findByTimeslotAndTeacherAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()),
                teacherRepository.findById(createLessonDto.getTeacherId()),
                createLessonDto.getDate()))
                .orElseThrow(() -> new ConflictException(
                                "Преподаватель с ID " + createLessonDto.getTeacherId() + " в это время занят"));
    }

    public void checkClassroomIsFree(CreateLessonDto createLessonDto) {
        Optional.ofNullable(lessonRepository.findByTimeslotAndClassroomAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()),
                classroomRepository.findById(createLessonDto.getClassroomId()),
                createLessonDto.getDate()))
                .orElseThrow(() -> new ConflictException(
                        "Аудитория с ID " + createLessonDto.getClassroomId() + " в это время занята"));
    }

    public void checkTimeslotIsFree(CreateLessonDto createLessonDto) {
        Optional.ofNullable(lessonRepository.findByTimeslotAndStudentGroupAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()),
                studentGroupRepository.findById(createLessonDto.getStudentGroupId()),
                createLessonDto.getDate())).orElseThrow(() -> new ConflictException(
                        "Таймслот с ID " + createLessonDto.getClassroomId() + " на дату "
                                + createLessonDto.getDate() + " занят у группы с ID "
                                + createLessonDto.getStudentGroupId()));
    }

    public void checkAccessibility(CreateLessonDto createLessonDto) {
        checkTeacherIsFree(createLessonDto);
        checkClassroomIsFree(createLessonDto);
        checkTimeslotIsFree(createLessonDto);
    }

}
