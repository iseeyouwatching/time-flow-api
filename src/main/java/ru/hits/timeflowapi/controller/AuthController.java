package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.dto.user.signup.EmployeeSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.StudentSignUpDto;
import ru.hits.timeflowapi.model.dto.user.signup.UserSignUpDto;
import ru.hits.timeflowapi.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("user/sign-up")
    public ResponseEntity<UserDto> userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDTO) {
        return new ResponseEntity<>(authService.userSignUp(userSignUpDTO), HttpStatus.OK);
    }

    @PostMapping("student/sign-up")
    public ResponseEntity<StudentDto> studentSignUp(@Valid @RequestBody StudentSignUpDto studentSignUpDTO) {
        return new ResponseEntity<>(authService.studentSignUp(studentSignUpDTO), HttpStatus.OK);
    }

    @PostMapping("employee/sign-up")
    public ResponseEntity<EmployeeDto> employeeSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDTO) {
        return new ResponseEntity<>(authService.employeeSignUp(employeeSignUpDTO), HttpStatus.OK);
    }

}
