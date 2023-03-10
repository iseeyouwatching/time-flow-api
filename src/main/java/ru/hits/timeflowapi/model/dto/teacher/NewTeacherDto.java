package ru.hits.timeflowapi.model.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.util.constants.RegexConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTeacherDto {

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
}
