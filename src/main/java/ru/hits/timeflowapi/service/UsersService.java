package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final UserMapper userMapper;

    private static final String property = "name";


    public Page<UserDto> getUsers(int pageNumber,
                                  int pageSize,
                                  Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, property);

        Page<UserEntity> users = userRepository.findAll(pageable);

        return users.map(userMapper::userToUserDto);
    }

    public Page<StudentDto> getStudents(int pageNumber,
                                        int pageSize,
                                        Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, property);

        Page<StudentDetailsEntity> students = studentDetailsRepository.findAll(pageable);

        return students.map(userMapper::studentDetailsToStudentDto);
    }

    public Page<EmployeeDto> getEmployees(int pageNumber,
                                          int pageSize,
                                          Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, property);

        Page<EmployeeDetailsEntity> employees = employeeDetailsRepository.findAll(pageable);

        return employees.map(userMapper::employeeDetailsToEmployeeDto);
    }
}