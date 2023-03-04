package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.repository.*;

import java.time.LocalDate;
import java.util.UUID;

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

    /**
     * Метод для проверки того, свободен преподаватель или нет.
     *
     * @param timeslotId уникальный идентификатор таймслота
     * @param teacherId  уникальный идентификатор преподавателя
     * @param date       дата
     *
     * @throws ConflictException исключение, которое выбрасывается, если преподаватель занят
     */
    public void checkTeacherIsFree(UUID timeslotId, UUID teacherId, LocalDate date) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndTeacherAndDate(
                timeslotRepository.findById(timeslotId),
                teacherRepository.findById(teacherId),
                date);

        if (lesson != null) {
            throw new ConflictException(
                    "Преподаватель с ID " + teacherId + " в это время занят");
        }
    }

    /**
     * Метод для проверки того, свободна аудитория или нет.
     *
     * @param timeslotId  уникальный идентификатор таймслота
     * @param classroomId уникальный идентификатор аудитории
     * @param date        дата
     *
     * @throws ConflictException исключение, которое выбрасывается, если аудитория занята
     */
    public void checkClassroomIsFree(UUID timeslotId, UUID classroomId, LocalDate date) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndClassroomAndDate(
                timeslotRepository.findById(timeslotId),
                classroomRepository.findById(classroomId),
                date);

        if (lesson != null) {
            throw new ConflictException(
                    "Аудитория с ID " + classroomId + " в это время занята");
        }
    }

    /**
     * Метод для проверки того, свободен таймслот у группы на конкретную дату или нет.
     *
     * @param timeslotId     уникальный идентификатор таймслота
     * @param studentGroupId уникальный идентификатор группы студентов
     * @param date           дата
     *
     * @throws ConflictException исключение, которое выбрасывается, если таймслот в эту дату у группы занят
     */
    public void checkTimeslotIsFree(UUID timeslotId, UUID studentGroupId, LocalDate date) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndStudentGroupAndDate(
                timeslotRepository.findById(timeslotId),
                studentGroupRepository.findById(studentGroupId),
                date);

        if (lesson != null) {
            throw new ConflictException(
                    "Таймслот с ID " + timeslotId + " на дату "
                            + date + " занят у группы с ID "
                            + studentGroupId);
        }
    }

    public void checkAccessibility(CreateLessonDto createLessonDto) {
        checkTeacherIsFree(createLessonDto.getTimeslotId(), createLessonDto.getTeacherId(),
                createLessonDto.getDate());
        checkClassroomIsFree(createLessonDto.getTimeslotId(), createLessonDto.getClassroomId(),
                createLessonDto.getDate());
        checkTimeslotIsFree(createLessonDto.getTimeslotId(), createLessonDto.getStudentGroupId(),
                createLessonDto.getDate());
    }

}
