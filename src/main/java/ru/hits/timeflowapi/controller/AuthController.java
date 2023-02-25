package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Регистрация.")
public class AuthController {

    private final AuthService authService;

    /**
     * Эндпоинт для регистрации внешнего пользователя.
     *
     * @param userSignUpDto информация для регистрации.
     * @return информация о зарегистрированном пользователе.
     */
    @Operation(summary = "Регистрация внешнего пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Внешний пользователь зарегистрирован."),
            @ApiResponse(responseCode = "400",
                    description = "Тело запроса не прошло валидацию. Ключ в теле ответа эквивалентен ключу " +
                            "в теле запроса, значение - требования валидации. Количество пар ключ-значений может " +
                            "меняться.",
                    content = @Content(examples = {
                            @ExampleObject(value = """
                                    {
                                      "name": "Имя должно быть написано на кириллице и с заглавной буквы."
                                      "password": "Длина пароля должна быть от 8 до 32 символов."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует.")
    })
    @PostMapping("user/sign-up")
    public ResponseEntity<UserDto> userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        return new ResponseEntity<>(authService.userSignUp(userSignUpDto), HttpStatus.OK);
    }

    /**
     * Эндпоинт для регистрации студента.
     *
     * @param studentSignUpDto информация о студенте для регистрации.
     * @return информация о зарегистрированном студенте.
     */
    @Operation(summary = "Регистрация студента.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Студент зарегистрирован."),
            @ApiResponse(responseCode = "400",
                    description = "Тело запроса не прошло валидацию. Ключ в теле ответа эквивалентен ключу " +
                            "в теле запроса, значение - требования валидации. Количество пар ключ-значений может " +
                            "меняться.",
                    content = @Content(examples = {
                            @ExampleObject(value = """
                                    {
                                      "name": "Имя должно быть написано на кириллице и с заглавной буквы."
                                      "password": "Длина пароля должна быть от 8 до 32 символов."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "404", description = "Группа с таким ID не найдена."),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует.")
    })
    @PostMapping("student/sign-up")
    public ResponseEntity<StudentDto> studentSignUp(@Valid @RequestBody StudentSignUpDto studentSignUpDto) {
        return new ResponseEntity<>(authService.studentSignUp(studentSignUpDto), HttpStatus.OK);
    }

    /**
     * Эндпоинт для регистрации сотрудников.
     *
     * @param employeeSignUpDto информация для регистрации.
     * @return информация о зарегистрированном сотруднике.
     */
    @Operation(summary = "Регистрация сотрудника.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник зарегистрирован."),
            @ApiResponse(responseCode = "400",
                    description = "Тело запроса не прошло валидацию. Ключ в теле ответа эквивалентен ключу " +
                            "в теле запроса, значение - требования валидации. Количество пар ключ-значений может " +
                            "меняться.",
                    content = @Content(examples = {
                            @ExampleObject(value = """
                                    {
                                      "name": "Имя должно быть написано на кириллице и с заглавной буквы."
                                      "password": "Длина пароля должна быть от 8 до 32 символов."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует.")
    })
    @PostMapping("employee/sign-up")
    public ResponseEntity<EmployeeDto> employeeSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return new ResponseEntity<>(authService.employeeSignUp(employeeSignUpDto), HttpStatus.OK);
    }

    /**
     * Эндпоинт для регистрации сотрудника с должностью составителя расписаний.
     *
     * @param employeeSignUpDto информация для регистрации
     * @return информация о зарегистрированном составителе расписаний.
     */
    @Operation(summary = "Регистрация сотрудника с должностью \"Составитель расписаний\".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник зарегистрирован."),
            @ApiResponse(responseCode = "400",
                    description = "Тело запроса не прошло валидацию. Ключ в теле ответа эквивалентен ключу " +
                            "в теле запроса, значение - требования валидации. Количество пар ключ-значений может " +
                            "меняться.",
                    content = @Content(examples = {
                            @ExampleObject(value = """
                                    {
                                      "name": "Имя должно быть написано на кириллице и с заглавной буквы."
                                      "password": "Длина пароля должна быть от 8 до 32 символов."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует.")
    })
    @PostMapping("employee/schedule-maker/sign-up")
    public ResponseEntity<EmployeeDto> schedulerMakerSignUp(@Valid @RequestBody EmployeeSignUpDto employeeSignUpDto) {
        return new ResponseEntity<>(authService.scheduleMakerSignUp(employeeSignUpDto), HttpStatus.OK);
    }

}
