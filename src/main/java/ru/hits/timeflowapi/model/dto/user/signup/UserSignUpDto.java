package ru.hits.timeflowapi.model.dto.user.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.enumeration.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSignUpDto {

    @Email(message = "Некорректный формат почты.")
    private String email;

    @Pattern(regexp = "[А-Я][а-я]*", message = "Имя должно быть написано на кириллице и с заглавной буквы.")
    private String name;

    @Pattern(regexp = "[А-Я][а-я]*", message = "Фамилия должна быть написана на кириллице и с заглавной буквы.")
    private String surname;

    @Pattern(regexp = "[А-Я][а-я]*", message = "Отчество должно быть написано на кириллице и с заглавной буквы.")
    private String patronymic;

    @Size(min = 8, max = 32, message = "Длина пароля должна быть от 8 до 32 символов.")
    private String password;

    private Sex sex;

}