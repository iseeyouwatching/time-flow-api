package ru.hits.timeflowapi.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.repository.*;

import java.util.UUID;

/**
 * Сервис, предназначенный для проверки существования ID
 * группы студентов/предмета/преподавателя/аудитории/таймслота.
 */
@Service
@RequiredArgsConstructor
public class CheckCreateLessonDtoValidity {

    private final StudentGroupRepository studentGroupRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final TimeslotRepository timeslotRepository;

    public void checkStudentGroupIdValidity(UUID id) {
        studentGroupRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Группы студентов с ID " + id + " не существует"));
    }

    public void checkSubjectIdValidity(UUID id) {
        subjectRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Предмета с ID " + id + " не существует"));
    }

    public void checkTeacherIdValidity(UUID id) {
        teacherRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Преподавателя с ID " + id + " не существует"));
    }

    public void checkClassroomIdValidity(UUID id) {
        classroomRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Аудитории с ID " + id + " не существует"));
    }

    public void checkTimeslotIdValidity(UUID id) {
        timeslotRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Таймслота с ID " + id + " не существует"));
    }

    public void checkIdValidity(CreateLessonDto createLessonDto) {
        checkStudentGroupIdValidity(createLessonDto.getStudentGroupId());
        checkSubjectIdValidity(createLessonDto.getSubjectId());
        checkTeacherIdValidity(createLessonDto.getTeacherId());
        checkClassroomIdValidity(createLessonDto.getClassroomId());
        checkTimeslotIdValidity(createLessonDto.getTimeslotId());
    }
}
