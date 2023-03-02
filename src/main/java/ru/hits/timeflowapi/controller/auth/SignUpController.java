package ru.hits.timeflowapi.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.signin.TokensDto;
import ru.hits.timeflowapi.model.dto.user.signup.EmployeeSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.StudentSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.UserSignUpDto;
import ru.hits.timeflowapi.service.auth.SignUpService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Регистрация")
public class SignUpController {
    private final SignUpService signUpService;

    @Operation(summary = "Регистрация внешнего пользователя.")
    @PostMapping("/sign-up/user")
    public TokensDto userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        return signUpService.userSignUp(userSignUpDto);
    }

    @Operation(summary = "Регистрация студента.")
    @PostMapping("/sign-up/student")
    public TokensDto studentSignUp(@Valid @RequestBody StudentSignUpDto studentSignUpDto) {
        return signUpService.studentSignUp(studentSignUpDto);
    }

    @Operation(summary = "Регистрация сотрудника.")
    @PostMapping("/sign-up/employee")
    public TokensDto employeeSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return signUpService.employeeSignUp(employeeSignUpDto);
    }

    @Operation(summary = "Регистрация сотрудника с должностью составителя расписаний.")
    @PostMapping("/sign-up/employee/schedule-maker")
    public TokensDto schedulerMakerSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return signUpService.scheduleMakerSignUp(employeeSignUpDto);
    }

}
