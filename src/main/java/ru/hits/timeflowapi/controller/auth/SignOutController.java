package ru.hits.timeflowapi.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.signin.RefreshTokenDto;
import ru.hits.timeflowapi.service.auth.SignOutService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Логин, логаут, работа с токенами")
public class SignOutController {

    private final SignOutService signOutService;

    @Operation(
            summary = "Логаут.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/sign-out")
    void logout(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        signOutService.signOut(getId(), refreshTokenDto.getRefreshToken());
    }

    private UUID getId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(((UserDetails) principal).getUsername());
    }

}
