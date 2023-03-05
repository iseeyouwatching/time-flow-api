package ru.hits.timeflowapi.model.dto.employeepost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для создания должности сотрудника.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEmployeePostDto {

    private String postRole;

    private String postName;

}
