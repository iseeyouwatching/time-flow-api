package ru.hits.timeflowapi.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EditEmailDto {

    @Schema(description = "Почта", example = "string@string.string")
    @Email(message = "Некорректный формат почты.")
    private String email;
}
