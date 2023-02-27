package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.EmployeePostDto;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {

    EmployeePostDto employeePostToDto(EmployeePostEntity entity) {
        return new EmployeePostDto(
                entity.getId(),
                entity.getPostRole(),
                entity.getPostName()
        );
    }

    List<EmployeePostDto> employeePostListToDtoList(List<EmployeePostEntity> entityList) {
        List<EmployeePostDto> dtoList = new ArrayList<>();

        for (EmployeePostEntity entity : entityList) {
            dtoList.add(employeePostToDto(entity));
        }

        return dtoList;
    }

}
