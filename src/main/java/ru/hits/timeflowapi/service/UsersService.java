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
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.repository.UserRepository;
import ru.hits.timeflowapi.service.helpingservices.CheckPaginationInfoService;

import static ru.hits.timeflowapi.model.enumeration.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    private final CheckPaginationInfoService checkPaginationInfoService;
    private final StudentDetailsRepository studentDetailsRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final UserMapper userMapper;
    private static final String PROPERTY = "UserName";
    private static final String PROPERTY_FOR_USER_ROLE = "Name";
    private static final Role ROLE = ROLE_USER;


    public Page<UserDto> getUsersPage(int pageNumber,
                                      int pageSize,
                                      Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, PROPERTY_FOR_USER_ROLE);
        checkPaginationInfoService.checkPagination(pageNumber, pageSize, direction);

        Page<UserEntity> users = userRepository.findAllByRole(pageable, ROLE);

        return users.map(userMapper::userToUserDto);
    }

    public Page<StudentDto> getStudentsPage(int pageNumber,
                                            int pageSize,
                                            Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, PROPERTY);
        checkPaginationInfoService.checkPagination(pageNumber, pageSize, direction);

        Page<StudentDetailsEntity> students = studentDetailsRepository.findAll(pageable);

        return students.map(userMapper::studentDetailsToStudentDto);
    }

    public Page<EmployeeDto> getEmployeesPage(int pageNumber,
                                              int pageSize,
                                              Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, PROPERTY);
        checkPaginationInfoService.checkPagination(pageNumber, pageSize, direction);

        Page<EmployeeDetailsEntity> employees = employeeDetailsRepository.findAll(pageable);

        return employees.map(userMapper::employeeDetailsToEmployeeDto);
    }
}