package ru.hits.timeflowapi.mapper;

import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.entity.SubjectEntity;

/**
 * Класс  маппер для преобразования объектов класса
 * SubjectEntity в объект класса SubjectDto и наоборот.
 */
public class SubjectMapper {
    /**
     * Метод для преобразования объекта класса {@link SubjectDto} в  обьект класса {@link SubjectEntity}.
     *
     * @param subjectDto то, что нужно замапить.
     * @return объект {@link SubjectEntity}.
     */
    public SubjectEntity SubjectDtoToEntity(SubjectDto subjectDto) {
        return SubjectEntity
                .builder()
                .id(subjectDto.getId())
                .name(subjectDto.getName())
                .build();
    }
}
