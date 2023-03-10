package ru.hits.timeflowapi.dto.signup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.enumeration.Sex;
import ru.hits.timeflowapi.util.constants.RegexConstant;
import ru.hits.timeflowapi.util.validation.annotation.UniqueContractNumberValidation;
import ru.hits.timeflowapi.util.validation.annotation.UniqueEmailValidation;

import javax.validation.constraints.*;

@Schema(description = "Информация о сотруднике")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeSignUpDto implements BasicSignUpUserDetails {

    @Schema(description = "Почта", example = "example_email@gmail.com")
    @NotEmpty(message = "Почта не может быть пустой.")
    @UniqueEmailValidation
    @Email(message = "Некорректный формат почты.")
    private String email;

    @Schema(description = "Имя", example = "Иван")
    @NotBlank(message = "Имя не может быть пустым.")
    @Pattern(regexp = RegexConstant.FULL_NAME_REGEX,
            message = "Недопустимый формат имени.")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    @NotBlank(message = "Фамилия не может быть пустой.")
    @Pattern(regexp = RegexConstant.FULL_NAME_REGEX,
            message = "Недопустимый формат фамилии.")
    private String surname;

    @Schema(description = "Отчество", example = "Иванович")
    @NotBlank(message = "Отчество не может быть пустым.")
    @Pattern(regexp = RegexConstant.FULL_NAME_REGEX,
            message = "Недопустимый формат отчества.")
    private String patronymic;

    @Schema(description = "Пол", example = "MALE")
    private Sex sex;

    @Schema(description = "Пароль", example = "Qwerty123")
    @NotBlank(message = "Пароль не может быть пустым.")
    @Size(min = 8, max = 32, message = "Длина пароля должна быть от 8 до 32 символов.")
    private String password;

    @Schema(description = "Номер трудового договора", example = "0000-23/01")
    @NotBlank(message = "Номер трудового договора не может быть пустым.")
    @Pattern(regexp = "\\d{4}-\\d{2}/((0\\d)|(1[1-2]))", message = "Некорректный формат номера трудового договора.")
    @UniqueContractNumberValidation
    private String contractNumber;

}