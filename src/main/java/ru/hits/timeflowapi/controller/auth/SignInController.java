package ru.hits.timeflowapi.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.dto.signin.RefreshTokenDto;
import ru.hits.timeflowapi.dto.signin.SignInDto;
import ru.hits.timeflowapi.dto.signin.TokensDto;
import ru.hits.timeflowapi.security.JWTService;
import ru.hits.timeflowapi.service.auth.SignInService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Логин, логаут, работа с токенами")
public class SignInController {

    private final SignInService signInService;
    private final JWTService jwtService;

    @Operation(summary = "Аутентификация пользователя.")
    @PostMapping("/sign-in")
    public TokensDto signIn(@Valid @RequestBody SignInDto signInDto) {
        return signInService.signIn(signInDto);
    }

    @Operation(summary = "Обновление пары токенов.")
    @PostMapping("/refresh-tokens")
    TokensDto updateTokens(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return jwtService.updateTokens(refreshTokenDto.getRefreshToken());
    }

}
