package ru.hits.timeflowapi.model.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshTokenDto {

    @NotBlank(message = "Токен не может быть пустым.")
    @NotNull(message = "Токен не может быть равен null.")
    private String refreshToken;

}
