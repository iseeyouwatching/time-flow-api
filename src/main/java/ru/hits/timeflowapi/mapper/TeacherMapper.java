package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.TeacherEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    /**
     * Метод для преобразования обьекта класса
     * TeacherEntity в обьект класса TeacherDto
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
}
