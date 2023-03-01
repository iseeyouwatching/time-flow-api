package ru.hits.timeflowapi.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.signin.SignInDto;
import ru.hits.timeflowapi.model.dto.signin.TokenDto;
import ru.hits.timeflowapi.service.auth.SignInService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class SignInController {

    private final SignInService signInService;

    @Operation(summary = "Аутентификация пользователя.")
    @PostMapping("/sign-in")
    public TokenDto signIn(@Valid @RequestBody SignInDto signInDto) {
        return signInService.signIn(signInDto);
    }

}
