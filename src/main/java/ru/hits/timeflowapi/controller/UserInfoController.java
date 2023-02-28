package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.user.*;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.service.CheckEmailService;
import ru.hits.timeflowapi.service.UserInfoService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Tag(name = "Личный кабинет")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CheckEmailService checkEmailService;

    private UUID extractId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    @Operation(
            summary = "Получить роль пользователя.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/role")
    Role getUserRole() {
        UUID id = extractId();
        return userInfoService.getUserRole(id);
    }

    @Operation(
            summary = "Получить общую информацию о пользователе.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/user")
    UserDto getUserInfo() {
        UUID id = extractId();
        return userInfoService.getUserInfo(id);
    }

    @Operation(
            summary = "Получить подробную информацию о студенте.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/student")
    StudentDto getStudentInfo() {
        UUID id = extractId();
        return userInfoService.getStudentDetailsInfo(id);
    }

    @Operation(
            summary = "Получить подробную информацию о сотруднике.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/employee")
    EmployeeDto getEmployeeInfo() {
        UUID id = extractId();
        return userInfoService.getEmployeeDetailsInfo(id);
    }

    @Operation(
            summary = "Изменить почту пользователя.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/email")
    UserDto updateEmail(@Valid @RequestBody EditEmailDto editEmailDto) {
        checkEmailService.checkEmail(editEmailDto.getEmail());
        UUID id = extractId();

        return userInfoService.updateEmail(id, editEmailDto);
    }

    @Operation(
            summary = "Изменить пароль пользователя.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/password")
    UserDto updatePassword(@Valid @RequestBody EditPasswordDto editPasswordDto) {
        UUID id = extractId();

        return userInfoService.updatePassword(id, editPasswordDto);
    }

}
