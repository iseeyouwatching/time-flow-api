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

        UserEntity userEntity = new UserEntity(
                userSignUpDTO.getEmail(),
                Role.ROLE_USER,
                userSignUpDTO.getName(),
                userSignUpDTO.getSurname(),
                userSignUpDTO.getPatronymic(),
                AccountStatus.ACTIVATE,
                passwordEncoder.encode(userSignUpDTO.getPassword()),
                userSignUpDTO.getSex()
        );

        UserEntity savedUser = userRepository.save(userEntity);

        return new UserDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getPatronymic(),
                savedUser.getAccountStatus(),
                savedUser.getSex()
        );
    }

    public StudentDto studentSignUp(StudentSignUpDto studentSignUpDTO) {
        checkEmail(studentSignUpDTO.getEmail());

        UserEntity user = new UserEntity(
                studentSignUpDTO.getEmail(),
                Role.ROLE_STUDENT,
                studentSignUpDTO.getName(),
                studentSignUpDTO.getSurname(),
                studentSignUpDTO.getPatronymic(),
                AccountStatus.PENDING,
                passwordEncoder.encode(studentSignUpDTO.getPassword()),
                studentSignUpDTO.getSex()
        );

        Optional<StudentGroupEntity> studentGroupEntity =
                studentGroupRepository.findById(studentSignUpDTO.getGroupId());

        if (studentGroupEntity.isEmpty()) {
            throw new NotFoundException("Группа с таким ID не найдена");
        }

        user = userRepository.save(user);

        StudentDetailsEntity studentDetails = new StudentDetailsEntity(
                user,
                studentSignUpDTO.getStudentNumber(),
                studentGroupEntity.get()
        );

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

        UserEntity user = new UserEntity(
                employeeSignUpDTO.getEmail(),
                Role.ROLE_EMPLOYEE,
                employeeSignUpDTO.getName(),
                employeeSignUpDTO.getSurname(),
                employeeSignUpDTO.getPatronymic(),
                AccountStatus.PENDING,
                passwordEncoder.encode(employeeSignUpDTO.getPassword()),
                employeeSignUpDTO.getSex()
        );

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
