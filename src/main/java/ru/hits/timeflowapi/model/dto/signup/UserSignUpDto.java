package ru.hits.timeflowapi.model.dto.signup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.enumeration.Sex;
import ru.hits.timeflowapi.util.validation.annotation.UniqueEmailValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "Информация о пользователе")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSignUpDto implements BasicSignUpUserDetails {

    @Schema(description = "Почта", example = "example_email@gmail.com")
    @NotEmpty(message = "Почта не может быть пустой.")
    @UniqueEmailValidation
    @Email(message = "Некорректный формат почты.")
    private String email;

    @Schema(description = "Имя", example = "Иван")
    @Pattern(regexp = "[А-Я][а-я]*", message = "Имя должно быть написано на кириллице и с заглавной буквы.")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    @Pattern(regexp = "[А-Я][а-я]*", message = "Фамилия должна быть написана на кириллице и с заглавной буквы.")
    private String surname;

    @Schema(description = "Отчество", example = "Иванович")
    @Pattern(regexp = "[А-Я][а-я]*", message = "Отчество должно быть написано на кириллице и с заглавной буквы.")
    private String patronymic;

    @Schema(description = "Пол", example = "MALE")
    private Sex sex;

    @Schema(description = "Пароль", example = "Qwerty123")
    @Size(min = 8, max = 32, message = "Длина пароля должна быть от 8 до 32 символов.")
    private String password;

}