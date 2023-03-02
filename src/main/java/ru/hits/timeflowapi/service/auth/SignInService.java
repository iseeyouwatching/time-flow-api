package ru.hits.timeflowapi.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.model.dto.signin.SignInDto;
import ru.hits.timeflowapi.model.dto.signin.TokensDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;
import ru.hits.timeflowapi.security.JWTService;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public TokensDto signIn(SignInDto signInDto) {
        UserEntity user = userRepository
                .findByEmail(signInDto.getEmail())
                .orElseThrow(() -> {
                    throw new UnauthorizedException("Некорректная почта и/или пароль.");
                });

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Некорректная почта и/или пароль.");
        }

        return jwtService.generateTokens(user.getId());
    }

}
