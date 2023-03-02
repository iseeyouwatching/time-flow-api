package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    List<LessonDto> lessonListToDtoList(List<LessonEntity> entityList) {
        List<LessonDto> dtoList = new ArrayList<>();

        if (entityList != null) {
            for (LessonEntity entity : entityList) {
                dtoList.add(new LessonDto(entity));
            }
        }

        return dtoList;
    }
}
