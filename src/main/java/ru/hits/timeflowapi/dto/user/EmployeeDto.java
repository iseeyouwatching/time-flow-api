package ru.hits.timeflowapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.dto.employeepost.EmployeePostDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {

    private UserDto userInfo;
    private String contractNumber;

    private List<EmployeePostDto> posts;
}