package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.signin.SignInDto;
import ru.hits.timeflowapi.model.dto.signin.TokenDto;
import ru.hits.timeflowapi.model.dto.user.signup.EmployeeSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.StudentSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.UserSignUpDto;
import ru.hits.timeflowapi.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Регистрация.")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация внешнего пользователя.")
    @PostMapping("/sign-up/user")
    public ResponseEntity<TokenDto> userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        return new ResponseEntity<>(authService.userSignUp(userSignUpDto), HttpStatus.OK);
    }

    @Operation(summary = "Регистрация студента.")
    @PostMapping("/sign-up/student")
    public ResponseEntity<TokenDto> studentSignUp(@Valid @RequestBody StudentSignUpDto studentSignUpDto) {
        return new ResponseEntity<>(authService.studentSignUp(studentSignUpDto), HttpStatus.OK);
    }

    @Operation(summary = "Регистрация сотрудника.")
    @PostMapping("/sign-up/employee")
    public ResponseEntity<TokenDto> employeeSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return new ResponseEntity<>(authService.employeeSignUp(employeeSignUpDto), HttpStatus.OK);
    }

    @Operation(summary = "Регистрация сотрудника с должностью \"Составитель расписаний\".")
    @PostMapping("/sign-up/schedule-maker/")
    public ResponseEntity<TokenDto> schedulerMakerSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return new ResponseEntity<>(authService.scheduleMakerSignUp(employeeSignUpDto), HttpStatus.OK);
    }

    @Operation(summary = "Аутентификация пользователя.")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody SignInDto signInDto) {
        return new ResponseEntity<>(authService.signIn(signInDto), HttpStatus.OK);
    }

}
