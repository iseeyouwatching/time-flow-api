package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.model.dto.user.EditEmailDto;
import ru.hits.timeflowapi.model.dto.user.EditPasswordDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto getUserInfo(String email) {
        UserDto userDto;
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UnauthorizedException("Не авторизован.");
        });

        userDto = (new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getAccountStatus(),
                user.getSex()));
        return userDto;
    }

    public UserDto PutPassword(String email, EditPasswordDto editPasswordDto) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Пользователь с таким id не найден");
                });

        user.setPassword(passwordEncoder.encode(editPasswordDto.getPassword()));

        user = userRepository.save(user);
        return getUserInfo(user.getEmail());
    }

    public UserDto PutEmail(String email, EditEmailDto editEmailDto) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Пользователь с таким id не найден");
                });

        user.setEmail(editEmailDto.getEmail());
        user = userRepository.save(user);
        return getUserInfo(user.getEmail());
    }


}
