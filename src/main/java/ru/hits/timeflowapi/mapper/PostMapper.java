package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.employeepost.EmployeePostDto;
import ru.hits.timeflowapi.model.dto.employeepost.NewEmployeePostDto;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для маппинга различных dto и {@link EmployeePostEntity}.
 */
@Component
@RequiredArgsConstructor
public class PostMapper {

    /**
     * Метод, который замапит {@link EmployeePostEntity} в {@link EmployeePostDto}.
     *
     * @param entity то, что нужно замапить.
     * @return объект {@link EmployeePostDto}, созданный на основе {@code entity}.
     */
    public EmployeePostDto employeePostToDto(EmployeePostEntity entity) {
        return new EmployeePostDto(
                entity.getId(),
                entity.getPostRole(),
                entity.getPostName()
        );
    }

    /**
     * Метод, который замапит список {@link EmployeePostEntity} в список {@link EmployeePostDto}.
     *
     * @param entityList то, что нужно замапить.
     * @return список {@link EmployeePostDto}, созданный на списке {@code entityList}.
     */
    public List<EmployeePostDto> employeePostListToDtoList(List<EmployeePostEntity> entityList) {
        List<EmployeePostDto> dtoList = new ArrayList<>();


        if (entityList != null) {
            for (EmployeePostEntity entity : entityList) {
                dtoList.add(employeePostToDto(entity));
            }
        }

        return dtoList;
    }

    /**
     * Метод, который замапит {@link NewEmployeePostDto} в {@link EmployeePostEntity}.
     *
     * @param newEmployeePostDto то, что нужно замапить.
     * @return объект {@link EmployeePostEntity}, созданный на основе {@code newEmployeePostDto}.
     */
    public EmployeePostEntity newEmployeePostDtoToEntity(NewEmployeePostDto newEmployeePostDto) {
        return EmployeePostEntity
                .builder()
                .postRole(newEmployeePostDto.getPostRole())
                .postName(newEmployeePostDto.getPostName())
                .build();
    }

}
