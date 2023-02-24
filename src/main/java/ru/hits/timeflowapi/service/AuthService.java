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
import ru.hits.timeflowapi.model.dto.user.signup.BasicSignUpUserDetails;
import ru.hits.timeflowapi.model.dto.user.signup.EmployeeSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.StudentSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.UserSignUpDto;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.repository.UserRepository;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestConfirmRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final EmployeeRequestConfirmRepository employeeRequestConfirmRepository;
    private final ScheduleMakerRequestConfirmRepository scheduleMakerRequestConfirmRepository;
    private final StudentRequestConfirmRepository studentRequestConfirmRepository;

    /**
     * Метод для регистрации внешнего пользователя.
     *
     * @param userSignUpDTO информация для регистрации внешнего пользователя.
     * @return сохраненная информация о внешнем пользователе.
     */
    public UserDto userSignUp(UserSignUpDto userSignUpDTO) {
        checkEmail(userSignUpDTO.getEmail());

        UserEntity user = buildUser(userSignUpDTO, Role.ROLE_USER, AccountStatus.ACTIVATE);

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

    /**
     * Метод для регистрации студента.
     *
     * @param studentSignUpDTO информация о студента для регистрации.
     * @return сохраненная информация о студенте.
     */
    public StudentDto studentSignUp(StudentSignUpDto studentSignUpDTO) {
        checkEmail(studentSignUpDTO.getEmail());

        UserEntity user = buildUser(studentSignUpDTO, Role.ROLE_STUDENT, AccountStatus.PENDING);

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

        StudentRequestConfirmEntity studentRequestConfirm = StudentRequestConfirmEntity
                .builder()
                .studentDetails(studentDetails)
                .isCompleted(false)
                .build();

        studentRequestConfirmRepository.save(studentRequestConfirm);

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

    /**
     * Метод для регистрации сотрудника.
     *
     * @param employeeSignUpDTO информация о сотруднике для регистрации.
     * @return сохраненная информация о сотруднике.
     */
    public EmployeeDto employeeSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        EmployeeDetailsEntity employeeDetails = basicEmployeeSignUp(employeeSignUpDTO);

        EmployeeRequestConfirmEntity employeeRequestConfirm = EmployeeRequestConfirmEntity
                .builder()
                .employeeDetails(employeeDetails)
                .isCompleted(false)
                .build();

        employeeRequestConfirmRepository.save(employeeRequestConfirm);

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

    /**
     * Метод для регистрации составителя расписаний.
     *
     * @param employeeSignUpDTO информация для регистрации составителя расписаний.
     * @return сохраненная информация о составителе расписаний.
     */
    public EmployeeDto scheduleMakerSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        EmployeeDetailsEntity employeeDetails = basicEmployeeSignUp(employeeSignUpDTO);

        ScheduleMakerRequestConfirmEntity scheduleMakerRequestConfirm = ScheduleMakerRequestConfirmEntity
                .builder()
                .employeeDetails(employeeDetails)
                .isCompleted(false)
                .build();

        scheduleMakerRequestConfirmRepository.save(scheduleMakerRequestConfirm);

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

    /**
     * Общая логика для создания и сохранения сущности сотрудника в БД.
     *
     * @param employeeSignUpDTO детали о сотруднике.
     * @return сохраненную сущность сотрудника в БД.
     */
    public EmployeeDetailsEntity basicEmployeeSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        checkEmail(employeeSignUpDTO.getEmail());

        UserEntity user = buildUser(employeeSignUpDTO, Role.ROLE_EMPLOYEE, AccountStatus.PENDING);

        user = userRepository.save(user);

        EmployeeDetailsEntity employeeDetails = EmployeeDetailsEntity
                .builder()
                .user(user)
                .contactNumber(employeeSignUpDTO.getContractNumber())
                .build();

        return employeeDetailsRepository.save(employeeDetails);
    }

    /**
     * Метод для создания сущности пользователя. <strong>Метод только создает сущность пользователя,
     * он не сохраняет её в БД!</strong>
     *
     * @param basicSignUpUserDetails информация о пользователе.
     * @param role                   роль пользователя.
     * @param accountStatus          статус аккаунта.
     * @return сущность пользователя.
     */
    private UserEntity buildUser(BasicSignUpUserDetails basicSignUpUserDetails, Role role, AccountStatus accountStatus) {
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

    /**
     * Метод для проверки существования пользователя с заданной почтой. Если эта почта занята,
     * то выбросится исключение.
     *
     * @param email почта.
     * @throws EmailAlreadyUsedException выбрасывается, если заданная почта уже используется.
     */
    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException("Пользователь с такой почтой уже существует");
        }
    }

}
