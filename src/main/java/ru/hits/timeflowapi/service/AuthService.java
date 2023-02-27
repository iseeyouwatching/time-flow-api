package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.signin.SignInDto;
import ru.hits.timeflowapi.model.dto.signin.TokenDto;
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
import ru.hits.timeflowapi.security.JWTUtil;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final EmployeeRequestConfirmRepository employeeRequestConfirmRepository;
    private final ScheduleMakerRequestConfirmRepository scheduleMakerRequestConfirmRepository;
    private final StudentRequestConfirmRepository studentRequestConfirmRepository;
    private final UserMapper userMapper;
    private final CheckEmailService checkEmailService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * Метод для регистрации внешнего пользователя.
     *
     * @param userSignUpDTO информация для регистрации внешнего пользователя.
     * @return сохраненная информация о внешнем пользователе.
     */
    public TokenDto userSignUp(UserSignUpDto userSignUpDTO) {
        checkEmailService.checkEmail(userSignUpDTO.getEmail());

        UserEntity user = userMapper.basicSignUpDetailsToUser(
                userSignUpDTO,
                Role.ROLE_USER,
                AccountStatus.ACTIVATE
        );

        user = userRepository.save(user);

        return new TokenDto(
                jwtUtil.generateToken(user.getEmail()),
                null
        );
    }

    /**
     * Метод для регистрации студента.
     *
     * @param studentSignUpDTO информация о студента для регистрации.
     * @return сохраненная информация о студенте.
     */
    public TokenDto studentSignUp(StudentSignUpDto studentSignUpDTO) {
        checkEmailService.checkEmail(studentSignUpDTO.getEmail());

        if (studentDetailsRepository.existsByStudentNumber(studentSignUpDTO.getStudentNumber())) {
            throw new ConflictException("Пользователь с таким номером студенческого билета уже существует");
        }

        UserEntity user = userMapper.basicSignUpDetailsToUser(
                studentSignUpDTO,
                Role.ROLE_STUDENT,
                AccountStatus.PENDING
        );

        StudentGroupEntity studentGroupEntity =
                studentGroupRepository
                        .findById(studentSignUpDTO.getGroupId())
                        .orElseThrow(() -> {
                            throw new NotFoundException("Группа с таким ID не найдена");
                        });

        user = userRepository.save(user);

        StudentDetailsEntity studentDetails = StudentDetailsEntity
                .builder()
                .user(user)
                .studentNumber(studentSignUpDTO.getStudentNumber())
                .group(studentGroupEntity)
                .build();

        studentDetails = studentDetailsRepository.save(studentDetails);

        StudentRequestConfirmEntity studentRequestConfirm = StudentRequestConfirmEntity
                .builder()
                .studentDetails(studentDetails)
                .isClosed(false)
                .creationDate(new Date())
                .build();

        studentRequestConfirmRepository.save(studentRequestConfirm);

        return new TokenDto(
                jwtUtil.generateToken(user.getEmail()),
                null
        );
    }

    /**
     * Метод для регистрации сотрудника.
     *
     * @param employeeSignUpDTO информация о сотруднике для регистрации.
     * @return токен.
     */
    public TokenDto employeeSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        if (employeeDetailsRepository.existsByContractNumber(employeeSignUpDTO.getContractNumber())) {
            throw new ConflictException("Пользователь с таким номером трудового договора уже существует");
        }

        EmployeeDetailsEntity employeeDetails = basicEmployeeSignUp(employeeSignUpDTO);

        EmployeeRequestConfirmEntity employeeRequestConfirm = EmployeeRequestConfirmEntity
                .builder()
                .employeeDetails(employeeDetails)
                .creationDate(new Date())
                .isClosed(false)
                .build();

        employeeRequestConfirmRepository.save(employeeRequestConfirm);

        return new TokenDto(
                jwtUtil.generateToken(employeeDetails.getUser().getEmail()),
                null
        );
    }

    /**
     * Метод для регистрации составителя расписаний.
     *
     * @param employeeSignUpDTO информация для регистрации составителя расписаний.
     * @return токен.
     */
    public TokenDto scheduleMakerSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        if (employeeDetailsRepository.existsByContractNumber(employeeSignUpDTO.getContractNumber())) {
            throw new ConflictException("Пользователь с таким номером трудового договора уже существует");
        }

        EmployeeDetailsEntity employeeDetails = basicEmployeeSignUp(employeeSignUpDTO);


        ScheduleMakerRequestConfirmEntity scheduleMakerRequestConfirm = ScheduleMakerRequestConfirmEntity
                .builder()
                .employeeDetails(employeeDetails)
                .creationDate(new Date())
                .isClosed(false)
                .build();

        scheduleMakerRequestConfirmRepository.save(scheduleMakerRequestConfirm);

        return new TokenDto(
                jwtUtil.generateToken(employeeDetails.getUser().getEmail()),
                null
        );
    }

    /**
     * Общая логика для создания и сохранения сущности сотрудника в БД.
     *
     * @param employeeSignUpDTO детали о сотруднике.
     * @return сохраненную сущность сотрудника в БД.
     */
    public EmployeeDetailsEntity basicEmployeeSignUp(EmployeeSignUpDto employeeSignUpDTO) {
        checkEmailService.checkEmail(employeeSignUpDTO.getEmail());

        UserEntity user = userMapper.basicSignUpDetailsToUser(
                employeeSignUpDTO,
                Role.ROLE_EMPLOYEE,
                AccountStatus.PENDING
        );

        user = userRepository.save(user);

        EmployeeDetailsEntity employeeDetails = EmployeeDetailsEntity
                .builder()
                .user(user)
                .contractNumber(employeeSignUpDTO.getContractNumber())
                .build();

        return employeeDetailsRepository.save(employeeDetails);
    }

    public TokenDto signIn(SignInDto signInDto) {
        UserEntity user = userRepository
                .findByEmail(signInDto.getEmail())
                .orElseThrow(() -> {
                    throw new UnauthorizedException("Неверный логин и/или пароль.");
                });

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(signInDto.getEmail());

            return new TokenDto(token, null);

        }

        throw new UnauthorizedException("Неверный логин и/или пароль.");
    }
}
