package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.entity.*;
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
     * @param id уникальный идентификатор группы студентов.
     * @throws BadRequestException исключение, которое выбрасывается, если группы студентов не существует.
     * @return сущность группы студентов.
     */
    public StudentGroupEntity checkStudentGroupIdValidity(UUID id) {
        StudentGroupEntity studentGroup = studentGroupRepository.findById(id).orElse(null);

        if (studentGroup == null) {
            throw new BadRequestException("Группы студентов с ID " + id + " не существует");
        }
        else {
            return studentGroup;
        }
    }

    /**
     * Метод для проверки существования учебного предмета.
     *
     * @param id уникальный идентификатор учебного предмета.
     * @throws BadRequestException исключение, которое выбрасывается, если учебного предмета не существует.
     * @return сущность предмета
     */
    public SubjectEntity checkSubjectIdValidity(UUID id) {
        SubjectEntity subject = subjectRepository.findById(id).orElse(null);

        if (subject == null) {
            throw new BadRequestException("Предмета с ID " + id + " не существует");
        }
        else {
            return subject;
        }
    }

    /**
     * Метод для проверки существования преподавателя.
     *
     * @param id уникальный идентификатор преподавателя.
     * @throws BadRequestException исключение, которое выбрасывается, если преподавателя не существует.
     * @return сущность преподавателя.
     */
    public TeacherEntity checkTeacherIdValidity(UUID id) {
        TeacherEntity teacher = teacherRepository.findById(id).orElse(null);

        if (teacher == null) {
            throw new BadRequestException("Преподавателя с ID " + id + " не существует");
        }
        else {
            return teacher;
        }
    }

    /**
     * Метод для проверки существования аудитории.
     *
     * @param id уникальный идентификатор аудитории.
     * @throws BadRequestException исключение, которое выбрасывается, если аудитории не существует.
     * @return сущность аудитории.
     */
    public ClassroomEntity checkClassroomIdValidity(UUID id) {
        ClassroomEntity classroom = classroomRepository.findById(id).orElse(null);

        if (classroom == null) {
            throw new BadRequestException("Аудитории с ID " + id + " не существует");
        }
        else {
            return classroom;
        }
    }

    /**
     * Метод для проверки существования таймслота.
     *
     * @param id уникальный идентификатор таймслота.
     * @throws BadRequestException исключение, которое выбрасывается, если таймслота не существует.
     * @return сущность таймслота.
     */
    public TimeslotEntity checkTimeslotIdValidity(UUID id) {
        TimeslotEntity timeslot = timeslotRepository.findById(id).orElse(null);

        if (timeslot == null) {
            throw new BadRequestException("Таймслота с ID " + id + " не существует");
        }
        else  {
            return timeslot;
        }
    }

    /**
     * Обобщающий метод для проверки существования группы студентов, учебного предмета, преподавателя
     * аудитории и таймслота по ID.
     *
     * @param createLessonDto данные, которые необходимы для создания/обновления пары.
     * @return LessonEntity, пара с валидными ID.
     */
    public LessonEntity checkIdValidity(CreateLessonDto createLessonDto) {
        StudentGroupEntity studentGroup = checkStudentGroupIdValidity(createLessonDto.getStudentGroupId());
        SubjectEntity subject= checkSubjectIdValidity(createLessonDto.getSubjectId());
        TeacherEntity teacher = checkTeacherIdValidity(createLessonDto.getTeacherId());
        ClassroomEntity classroom = checkClassroomIdValidity(createLessonDto.getClassroomId());
        TimeslotEntity timeslot = checkTimeslotIdValidity(createLessonDto.getTimeslotId());

        LessonEntity lesson = new LessonEntity();
        lesson.setStudentGroup(studentGroup);
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        lesson.setClassroom(classroom);
        lesson.setTimeslot(timeslot);
        lesson.setDate(createLessonDto.getDate());
        lesson.setLessonType(createLessonDto.getLessonType());

        return lesson;
    }
}
