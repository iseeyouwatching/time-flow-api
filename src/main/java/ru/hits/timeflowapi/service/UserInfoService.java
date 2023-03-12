package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.dto.employeepost.EmployeePostDto;
import ru.hits.timeflowapi.dto.user.*;
import ru.hits.timeflowapi.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.enumeration.Role;
import ru.hits.timeflowapi.exception.ForbiddenException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserInfo(UUID id) {
        UserEntity user = getUserById(id);

        return userMapper.userToUserDto(user);
    }

    public Role getUserRole(UUID id) {
        UserEntity user = getUserById(id);

        return userMapper.userToUserDto(user).getRole();
    }

    public StudentDto getStudentDetailsInfo(UUID id) {
        StudentDetailsEntity studentDetails = getStudentById(id);

        return userMapper.studentDetailsToStudentDto(studentDetails);
    }

    public List<EmployeePostDto> getUserPost(UUID id) {
        EmployeeDetailsEntity employeeDetails = getEmployeePostById(id);

        return userMapper.employeeDetailsToEmployeeDto(employeeDetails).getPosts();
    }

    public EmployeeDto getEmployeeDetailsInfo(UUID id) {
        EmployeeDetailsEntity employeeDetails = getEmployeeById(id);

        return userMapper.employeeDetailsToEmployeeDto(employeeDetails);
    }

    public UserDto updatePassword(UUID id, EditPasswordDto editPasswordDto) {
        UserEntity user = getUserById(id);

        user.setPassword(passwordEncoder.encode(editPasswordDto.getPassword()));
        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    public UserDto updateEmail(UUID id, EditEmailDto editEmailDto) {
        UserEntity user = getUserById(id);

        user.setEmail(editEmailDto.getEmail());
        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    private UserEntity getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Не авторизован.");
                });
    }

    private StudentDetailsEntity getStudentById(UUID id) {
        return studentDetailsRepository
                .findByUserId(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Не присутствует в числе студентов.");
                });
    }

    private EmployeeDetailsEntity getEmployeeById(UUID id) {
        return employeeDetailsRepository
                .findByUserId(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Не присутствует в числе сотрудников.");
                });
    }

    private EmployeeDetailsEntity getEmployeePostById(UUID id) {
        return employeeDetailsRepository
                .findByUserId(id)
                .orElseThrow(() -> {
                    throw new ForbiddenException("Пользователь не является сотрудником.");
                });
    }
}