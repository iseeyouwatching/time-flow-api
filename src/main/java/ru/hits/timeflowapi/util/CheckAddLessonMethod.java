package ru.hits.timeflowapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.repository.LessonRepository;
import ru.hits.timeflowapi.repository.TeacherRepository;
import ru.hits.timeflowapi.repository.TimeslotRepository;

@Component
@RequiredArgsConstructor
public class CheckAddLessonMethod {

    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;
    private final TeacherRepository teacherRepository;

    public void checkTeacherIsFree(CreateLessonDto createLessonDto) {
        LessonEntity lesson = lessonRepository.findByTimeslotAndTeacherAndDate(
                timeslotRepository.findById(createLessonDto.getTimeslotId()).orElse(null),
                teacherRepository.findById(createLessonDto.getTeacherId()).orElse(null),
                createLessonDto.getDate());

        if (lesson != null) {
            throw new ConflictException("Преподаватель с ID " + createLessonDto.getTeacherId() + " в это время занят");
        }
    }

    private void checkAddLessonMethod(CreateLessonDto createLessonDto) {
        checkTeacherIsFree(createLessonDto);
    }

}
