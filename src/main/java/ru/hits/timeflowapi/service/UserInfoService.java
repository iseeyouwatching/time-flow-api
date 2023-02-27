package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.user.EditEmailDto;
import ru.hits.timeflowapi.model.dto.user.EditPasswordDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserInfo(String email) {
        UserDto userDto;
        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new UnauthorizedException("Не авторизован.");
                });

        return userMapper.userToUserDto(user);
    }

    public UserDto PutPassword(String email, EditPasswordDto editPasswordDto) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Пользователь с таким id не найден");
                });

        user.setPassword(passwordEncoder.encode(editPasswordDto.getPassword()));

        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public UserDto PutEmail(String email, EditEmailDto editEmailDto) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Пользователь с таким id не найден");
                });

        user.setEmail(editEmailDto.getEmail());
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }
}