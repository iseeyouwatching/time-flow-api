package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.EmailAlreadyUsedException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.dto.user.signup.EmployeeSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.StudentSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.UserSignUpDto;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final StudentGroupRepository studentGroupRepository;

    public UserDto userSignUp(UserSignUpDto userSignUpDTO) {
        checkEmail(userSignUpDTO.getEmail());

        UserEntity user = UserEntity
                .builder()
                .email(userSignUpDTO.getEmail())
                .role(Role.ROLE_EMPLOYEE)
                .name(userSignUpDTO.getName())
                .surname(userSignUpDTO.getSurname())
                .patronymic(userSignUpDTO.getPatronymic())
                .accountStatus(AccountStatus.PENDING)
                .password(passwordEncoder.encode(userSignUpDTO.getPassword()))
                .sex(userSignUpDTO.getSex())
                .build();

        user = userRepository.save(user);

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

    public StudentDto studentSignUp(StudentSignUpDto studentSignUpDTO) {
        checkEmail(studentSignUpDTO.getEmail());

        UserEntity user = UserEntity
                .builder()
                .email(studentSignUpDTO.getEmail())
                .role(Role.ROLE_EMPLOYEE)
                .name(studentSignUpDTO.getName())
                .surname(studentSignUpDTO.getSurname())
                .patronymic(studentSignUpDTO.getPatronymic())
                .accountStatus(AccountStatus.PENDING)
                .password(passwordEncoder.encode(studentSignUpDTO.getPassword()))
                .sex(studentSignUpDTO.getSex())
                .build();

        Optional<StudentGroupEntity> studentGroupEntity =
                studentGroupRepository.findById(studentSignUpDTO.getGroupId());

        if (studentGroupEntity.isEmpty()) {
            throw new NotFoundException("Группа с таким ID не найдена");
        }

        user = userRepository.save(user);

        StudentDetailsEntity studentDetails = StudentDetailsEntity
                .builder()
                .user(user)
                .studentNumber(studentSignUpDTO.getStudentNumber())
                .group(studentGroupEntity.get())
                .build();

        studentDetails = studentDetailsRepository.save(studentDetails);

        return new StudentDto(
                studentDetails.getUser().getId(),
                studentDetails.getUser().getEmail(),
                studentDetails.getUser().getRole(),
                studentDetails.getUser().getName(),
                studentDetails.getUser().getSurname(),
                studentDetails.getUser().getPatronymic(),
                studentDetails.getUser().getAccountStatus(),
                studentDetails.getUser().getSex(),
                studentDetails.getStudentNumber(),
                new StudentGroupBasicDto(
                        studentDetails.getGroup().getId(),
                        studentDetails.getGroup().getNumber()
                ));

    }

    public EmployeeDto employeeSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        checkEmail(employeeSignUpDTO.getEmail());

        UserEntity user = UserEntity
                .builder()
                .email(employeeSignUpDTO.getEmail())
                .role(Role.ROLE_EMPLOYEE)
                .name(employeeSignUpDTO.getName())
                .surname(employeeSignUpDTO.getSurname())
                .patronymic(employeeSignUpDTO.getPatronymic())
                .accountStatus(AccountStatus.PENDING)
                .password(passwordEncoder.encode(employeeSignUpDTO.getPassword()))
                .sex(employeeSignUpDTO.getSex())
                .build();

        user = userRepository.save(user);

        EmployeeDetailsEntity employeeDetails = EmployeeDetailsEntity
                .builder()
                .user(user)
                .contactNumber(employeeSignUpDTO.getContractNumber())
                .build();

        employeeDetails = employeeDetailsRepository.save(employeeDetails);

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

    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException("Пользователь с такой почтой уже существует");
        }
    }

}
