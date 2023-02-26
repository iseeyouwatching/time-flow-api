package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final UserMapper userMapper;

    public StudentRequestConfirmDto studentRequestConfirmToDto(StudentRequestConfirmEntity requestEntity) {
        return new StudentRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.studentDetailsToStudentDto(requestEntity.getStudentDetails())
        );
    }

    public EmployeeRequestConfirmDto employeeRequestConfirmToDto(EmployeeRequestConfirmEntity requestEntity) {
        return new EmployeeRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.employeeDetailsToEmployeeDto(requestEntity.getEmployeeDetails())
        );
    }

    public EmployeeRequestConfirmDto employeeRequestConfirmToDto(ScheduleMakerRequestConfirmEntity requestEntity) {
        return new EmployeeRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                userMapper.employeeDetailsToEmployeeDto(requestEntity.getEmployeeDetails())
        );
    }

}
