package ru.hits.timeflowapi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.dto.user.signup.BasicSignUpUserDetails;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserDto userToUserDto(UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getAccountStatus(),
                user.getSex()
        );
    }

    public StudentDto studentDetailsToStudentDto(StudentDetailsEntity studentDetails) {
        return new StudentDto(
                studentDetails.getUser().getId(),
                studentDetails.getUser().getEmail(),
                studentDetails.getUser().getRole(),
                studentDetails.getUser().getName(),
                studentDetails.getUser().getSurname(),
                studentDetails.getUser().getPatronymic(),
                studentDetails.getUser().getAccountStatus(),
                studentDetails.getUser().getSex(),
                studentDetails.getUser().getStudent().getStudentNumber(),
                new StudentGroupBasicDto(
                        studentDetails.getGroup().getId(),
                        studentDetails.getGroup().getNumber()
                )
        );
    }

    public EmployeeDto employeeDetailsToEmployeeDto(EmployeeDetailsEntity employeeDetails) {
        return new EmployeeDto(
                employeeDetails.getUser().getId(),
                employeeDetails.getUser().getEmail(),
                employeeDetails.getUser().getRole(),
                employeeDetails.getUser().getName(),
                employeeDetails.getUser().getSurname(),
                employeeDetails.getUser().getPatronymic(),
                employeeDetails.getUser().getAccountStatus(),
                employeeDetails.getUser().getSex(),
                employeeDetails.getContactNumber()
        );
    }

    public UserEntity basicSignUpDetailsToUser(BasicSignUpUserDetails basicSignUpUserDetails,
                                               Role role,
                                               AccountStatus accountStatus
    ) {
        return UserEntity
                .builder()
                .email(basicSignUpUserDetails.getEmail())
                .role(role)
                .name(basicSignUpUserDetails.getName())
                .surname(basicSignUpUserDetails.getSurname())
                .patronymic(basicSignUpUserDetails.getPatronymic())
                .accountStatus(accountStatus)
                .password(passwordEncoder.encode(basicSignUpUserDetails.getPassword()))
                .sex(basicSignUpUserDetails.getSex())
                .build();
    }

}
