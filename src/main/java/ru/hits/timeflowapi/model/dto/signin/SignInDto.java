package ru.hits.timeflowapi.model.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInDto {

    @NotNull(message = "Почта не может быть пустой.")
    @NotBlank(message = "Почта не может быть пустой.")
    private String email;

    @NotNull(message = "Пароль не может быть пустым.")
    private String password;

}
