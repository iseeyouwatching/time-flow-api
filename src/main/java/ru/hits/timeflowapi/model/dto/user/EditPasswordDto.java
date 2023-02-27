package ru.hits.timeflowapi.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditPasswordDto {

    @Schema(description = "Пароль", example = "Qwerty123")
    @Size(min = 8, max = 32, message = "Длина пароля должна быть от 8 до 32 символов.")
    private String password;

}
