package ru.hits.timeflowapi.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.exception.RefreshTokenNotValidException;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignOutService {

    private final UserRepository userRepository;

    public void signOut(UUID userId, String refreshToken) {
        UserEntity user = userRepository
                .findByIdAndRefreshToken(userId, refreshToken)
                .orElseThrow(() -> {
                    throw new RefreshTokenNotValidException("Невалидный refresh токен.");
                });

        user.setRefreshToken(null);
        userRepository.save(user);
    }

}
