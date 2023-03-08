package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.dto.lesson.CreateLessonForAFewWeeksDto;

/**
 * Класс маппер для преобразования объекта класса
 * CreateLessonForAFewWeeksDto в объект класса CreateLessonDto.
 */
@Component
@RequiredArgsConstructor
public class CreateLessonForAFewWeeksDtoMapper {

    /**
     * Метод для преобразования объекта класса
     * CreateLessonForAFewWeeksDto в объект класса CreateLessonDto.
     *
     * @param createLessonForAFewWeeksDto объект класса CreateLessonForAFewWeeksDto
     * @return createLessondDto объект класса CreateLessonDto
     */
    public CreateLessonDto createLessonForAFewWeeksDtoToCreateLessonDto(CreateLessonForAFewWeeksDto
                                                                         createLessonForAFewWeeksDto) {

        CreateLessonDto createLessonDto = new CreateLessonDto();

        createLessonDto.setStudentGroupId(createLessonForAFewWeeksDto.getStudentGroupId());
        createLessonDto.setSubjectId(createLessonForAFewWeeksDto.getSubjectId());
        createLessonDto.setTeacherId(createLessonForAFewWeeksDto.getTeacherId());
        createLessonDto.setClassroomId(createLessonForAFewWeeksDto.getClassroomId());
        createLessonDto.setTimeslotId(createLessonForAFewWeeksDto.getTimeslotId());
        createLessonDto.setDate(createLessonForAFewWeeksDto.getDate());
        createLessonDto.setLessonType(createLessonForAFewWeeksDto.getLessonType());

        return createLessonDto;

    }
}
