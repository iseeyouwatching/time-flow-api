package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.NewSubjectDto;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.entity.SubjectEntity;

/**
 * Класс  маппер для преобразования объектов класса
 * SubjectEntity в объект класса SubjectDto и наоборот.
 */
@Component
@RequiredArgsConstructor
public class SubjectMapper {
    /**
     * Метод для преобразования объекта класса {@link SubjectDto} в  обьект класса {@link SubjectEntity}.
     *
     * @param newSubjectDto то, что нужно замапить.
     * @return объект {@link SubjectEntity}.
     */
    public SubjectEntity NewSubjectDtoToEntity(NewSubjectDto newSubjectDto) {
        return SubjectEntity
                .builder()
                .name(newSubjectDto.getName())
                .build();
    }
}
