package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.service.UsersService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Админ")
public class UserDetailsController {

    private final UsersService usersService;

    @GetMapping("/users")
    @Operation(
            summary = "Получить всех внешних пользователей.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    Page<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {

        return usersService.getUsersPage(
                pageNumber,
                pageSize,
                sortDirection
        );
    }

    @GetMapping("/students")
    @Operation(
            summary = "Получить всех студентов.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    Page<StudentDto> getStudents(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {

        return usersService.getStudentsPage(
                pageNumber,
                pageSize,
                sortDirection
        );
    }

    @GetMapping("/employees")
    @Operation(
            summary = "Получить всех сотрудников.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    Page<EmployeeDto> getEmployees(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {

        return usersService.getEmployeesPage(
                pageNumber,
                pageSize,
                sortDirection
        );
    }
}
