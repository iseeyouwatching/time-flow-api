package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.TeacherEntity;

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
     * Метод для преобразования объекта класса {@link TeacherDto} в  обьект класса {@link TeacherEntity}.
     *
     * @param teacherDto то, что нужно замапить.
     * @return объект {@link TeacherEntity}.
     */
    public TeacherEntity TeacherDtoToEntity(TeacherDto teacherDto) {
        return TeacherEntity
                .builder()
                .id(teacherDto.getId())
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .patronymic(teacherDto.getPatronymic())
                .build();
    }
}
