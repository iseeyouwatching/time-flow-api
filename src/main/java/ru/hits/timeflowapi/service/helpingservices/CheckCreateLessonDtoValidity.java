package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.exception.ConflictException;
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

    /**
     * Метод для проверки существования группы студентов.
     *
     * @param id уникальный идентификатор группы студентов
     * @throws BadRequestException исключение, которое выбрасывается, если группы студентов не существует
     */
    public void checkStudentGroupIdValidity(UUID id) {
        studentGroupRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Группы студентов с ID " + id + " не существует"));
    }

    /**
     * Метод для проверки существования учебного предмета.
     *
     * @param id уникальный идентификатор учебного предмета
     * @throws BadRequestException исключение, которое выбрасывается, если учебного предмета не существует
     */
    public void checkSubjectIdValidity(UUID id) {
        subjectRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Предмета с ID " + id + " не существует"));
    }

    /**
     * Метод для проверки существования преподавателя.
     *
     * @param id уникальный идентификатор преподавателя
     * @throws BadRequestException исключение, которое выбрасывается, если преподавателя не существует
     */
    public void checkTeacherIdValidity(UUID id) {
        teacherRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Преподавателя с ID " + id + " не существует"));
    }

    /**
     * Метод для проверки существования аудитории.
     *
     * @param id уникальный идентификатор аудитории
     * @throws BadRequestException исключение, которое выбрасывается, если аудитории не существует
     */
    public void checkClassroomIdValidity(UUID id) {
        classroomRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Аудитории с ID " + id + " не существует"));
    }

    /**
     * Метод для проверки существования таймслота.
     *
     * @param id уникальный идентификатор таймслота
     * @throws BadRequestException исключение, которое выбрасывается, если таймслота не существует
     */
    public void checkTimeslotIdValidity(UUID id) {
        timeslotRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Таймслота с ID " + id + " не существует"));
    }

    /**
     * Обобщающий метод для проверки существования группы студентов, учебного предмета, преподавателя
     * аудитории и таймслота по ID.
     *
     * @param createLessonDto данные, которые необходимы для создания/обновления пары
     */
    public void checkIdValidity(CreateLessonDto createLessonDto) {
        checkStudentGroupIdValidity(createLessonDto.getStudentGroupId());
        checkSubjectIdValidity(createLessonDto.getSubjectId());
        checkTeacherIdValidity(createLessonDto.getTeacherId());
        checkClassroomIdValidity(createLessonDto.getClassroomId());
        checkTimeslotIdValidity(createLessonDto.getTimeslotId());
    }
}
