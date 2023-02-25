package ru.hits.timeflowapi.model.dto.user.signup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.enumeration.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "Информация о сотруднике")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeSignUpDto implements BasicSignUpUserDetails {

    @Schema(description = "Почта", example = "string@string.string")
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

    @Schema(description = "Номер трудового договора", example = "123456")
    @Size(min = 6, max = 6, message = "Длина номера трудового договора должна быть 6 символов.")
    private String contractNumber;

}