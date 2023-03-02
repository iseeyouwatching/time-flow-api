package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.request.EmployeeRequestDto;
import ru.hits.timeflowapi.model.dto.request.StudentRequestDto;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestEntity;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final UserMapper userMapper;

    public StudentRequestDto studentRequestToDto(StudentRequestEntity requestEntity) {
        return new StudentRequestDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.studentDetailsToStudentDto(requestEntity.getStudentDetails())
        );
    }

    public EmployeeRequestDto employeeRequestToDto(EmployeeRequestEntity requestEntity) {
        return new EmployeeRequestDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.employeeDetailsToEmployeeDto(requestEntity.getEmployeeDetails())
        );
    }

    public EmployeeRequestDto employeeRequestToDto(ScheduleMakerRequestEntity requestEntity) {
        return new EmployeeRequestDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.employeeDetailsToEmployeeDto(requestEntity.getEmployeeDetails())
        );
    }

}
