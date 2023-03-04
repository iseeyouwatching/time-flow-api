package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassroomMapper {

    /**
     * Метод для преобразования обьекта класса
     * ClassroomEntity в обьект класса ClassroomDto
     *
     * @param entityList список ClassroomEntity
     */
    public List<ClassroomDto> classroomListToDtoList(List<ClassroomEntity> entityList) {
        List<ClassroomDto> dtoList = new ArrayList<>();

        if (entityList != null) {
            for (ClassroomEntity entity : entityList) {
                dtoList.add(new ClassroomDto(entity));
            }
        }
        return dtoList;
    }
}
