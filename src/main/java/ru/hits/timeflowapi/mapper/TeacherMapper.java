package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.entity.TeacherEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс  маппер для преобразования объектов класса
 * TeacherEntity в объект класса TeacherDto и наоборот.
 */
@Component
@RequiredArgsConstructor
public class TeacherMapper {

    /**
     * Метод для преобразования объекта класса
     * {@link TeacherEntity} в объект класса {@link TeacherDto}.
     *
     * @param entityList список TeacherEntity
     */
    public List<TeacherDto> teacherListToDtoList(List<TeacherEntity> entityList) {
        List<TeacherDto> dtoList = new ArrayList<>();

        if (entityList != null) {
            for (TeacherEntity entity : entityList) {
                dtoList.add(new TeacherDto(entity));
            }
        }
        return dtoList;
    }

    /**
     * Метод для преобразования объекта класса {@link NewTeacherDto} в  обьект класса {@link TeacherEntity}.
     *
     * @param newTeacherDto то, что нужно замапить.
     * @return объект {@link TeacherEntity}.
     */
    public TeacherEntity newTeacherDtoToEntity(NewTeacherDto newTeacherDto) {
        return TeacherEntity
                .builder()
                .name(newTeacherDto.getName())
                .surname(newTeacherDto.getSurname())
                .patronymic(newTeacherDto.getPatronymic())
                .build();
    }
}
