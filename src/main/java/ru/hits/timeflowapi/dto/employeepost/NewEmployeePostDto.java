package ru.hits.timeflowapi.dto.employeepost;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.util.constants.RegexConstant;
import ru.hits.timeflowapi.util.validation.annotation.UniquePostNameValidation;
import ru.hits.timeflowapi.util.validation.annotation.UniquePostRoleValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO для создания должности сотрудника.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEmployeePostDto {

    @Schema(example = "ROLE_CLEANER")
    @NotBlank(message = "Роль должности не может быть пустым или null.")
    @Pattern(regexp = RegexConstant.POST_ROLE_REGEX, message = "Некорректный формат названия роли.")
    @UniquePostRoleValidation
    private String postRole;

    @Schema(example = "Уборщик")
    @NotBlank(message = "Название должности не может быть пустым или null.")
    @Pattern(regexp = RegexConstant.POST_NAME_REGEX, message = "Некорректный формат названия должности.")
    @UniquePostNameValidation
    private String postName;

}
