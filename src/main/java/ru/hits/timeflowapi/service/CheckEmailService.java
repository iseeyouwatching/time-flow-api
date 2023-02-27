package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.EmailAlreadyUsedException;
import ru.hits.timeflowapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CheckEmailService {

    private final UserRepository userRepository;

    /**
     * Метод для проверки существования пользователя с заданной почтой. Если эта почта занята,
     * то выбросится исключение.
     *
     * @param email почта.
     * @throws EmailAlreadyUsedException выбрасывается, если заданная почта уже используется.
     */
    public void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException("Пользователь с такой почтой уже существует");
        }
    }
}
