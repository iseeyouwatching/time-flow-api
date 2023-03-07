package ru.hits.timeflowapi.dto.employeepost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO с информацией о должности сотрудника.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePostDto {

    private UUID id;

    private String postRole;

    private String postName;

}
