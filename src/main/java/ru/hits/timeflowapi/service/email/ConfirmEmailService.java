package ru.hits.timeflowapi.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.dto.user.UserDto;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.exception.EmailAlreadyConfirmedException;
import ru.hits.timeflowapi.exception.InvalidEmailConfirmLinkException;
import ru.hits.timeflowapi.exception.LinkExpiredException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.repository.UserRepository;
import ru.hits.timeflowapi.service.StringCryptographer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmEmailService {

    private final UserRepository userRepository;
    private final StringCryptographer cryptographer;
    private final UserMapper userMapper;

    public UserDto confirmEmail(String encryptedPayload) {
        encryptedPayload = encryptedPayload.replace(' ', '+');      //'+' - зарезервированный символ
        String decryptedPayload = cryptographer.decryptString(encryptedPayload);

        String[] payloadArr = decryptedPayload.split("/");
        UUID id = UUID.fromString(payloadArr[0]);
        String email = payloadArr[1];
        LocalDateTime expDate = LocalDateTime.parse(payloadArr[2]);

        if (LocalDate.now(ZoneId.systemDefault()).isBefore(ChronoLocalDate.from(expDate))) {
            UserEntity user = userRepository
                    .findById(id)
                    .orElseThrow(() -> {
                        throw new InvalidEmailConfirmLinkException("Некорректная ссылка");
                    });

            if (user.getEmail().equals(email)) {
                if (!user.isEmailConfirmed()) {
                    user.setEmailConfirmed(true);
                    userRepository.save(user);

                    log.info(user.getEmail());
                    log.info(user.getId().toString());

                    return userMapper.userToUserDto(user);
                } else {
                    throw new EmailAlreadyConfirmedException("Почта уже подтверждена.");
                }
            } else {
                throw new InvalidEmailConfirmLinkException("Некорректная ссылка.");
            }
        } else {
            throw new LinkExpiredException("Срок действия ссылки истек.");
        }
    }

}
