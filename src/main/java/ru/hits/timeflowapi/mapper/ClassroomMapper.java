package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс  маппер для преобразования объекта класса
 * ClassroomEntity в объект класса ClassroomDto.
 */
@Component
@RequiredArgsConstructor
public class ClassroomMapper {

    /**
     * Метод для преобразования объекта класса
     * ClassroomEntity в объект класса ClassroomDto.
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

    /**
     * Метод для преобразования объекта класса {@link ClassroomDto} в  обьект класса {@link ClassroomEntity}.
     *
     * @param classroomDto то, что нужно замапить.
     * @return объект {@link ClassroomEntity}.
     */
    public ClassroomEntity classroomDtoToEntity(ClassroomDto classroomDto) {
        return ClassroomEntity
                .builder()
                .id(classroomDto.getId())
                .number(classroomDto.getNumber())
                .build();
    }
}
