package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;

/**
 * Класс  маппер для преобразования объектов класса
 * StudentGroupEntity в объект класса StudentGroupBasicDto и наоборот.
 */
@Component
@RequiredArgsConstructor
public class StudentGroupMapper {
    /**
     * Метод для преобразования объекта класса {@link StudentGroupBasicDto} в  обьект класса {@link StudentGroupEntity}.
     *
     * @param studentGroupBasicDto то, что нужно замапить.
     * @return объект {@link StudentGroupEntity}.
     */
    public StudentGroupEntity StudentGroupDtoToEntity(StudentGroupBasicDto studentGroupBasicDto) {
        return StudentGroupEntity
                .builder()
                .id(studentGroupBasicDto.getId())
                .number(studentGroupBasicDto.getNumber())
                .build();
    }
}