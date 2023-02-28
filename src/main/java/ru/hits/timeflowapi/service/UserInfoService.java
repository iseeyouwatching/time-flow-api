package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.user.*;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.UserRepository;

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

        if (editEmailDto.getEmail().length() != 0) {
            user.setEmail(editEmailDto.getEmail());
            user = userRepository.save(user);
            return userMapper.userToUserDto(user);
        } else {
            throw new BadRequestException("Email не может быть null.");
        }
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
}