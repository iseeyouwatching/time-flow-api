package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.user.EditEmailDto;
import ru.hits.timeflowapi.model.dto.user.EditPasswordDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserInfo(UUID id) {
        UserEntity user = getUserById(id);

        return userMapper.userToUserDto(user);
    }

    public UserDto updatePassword(UUID id, EditPasswordDto editPasswordDto) {
        UserEntity user = getUserById(id);

        user.setPassword(passwordEncoder.encode(editPasswordDto.getPassword()));
        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    public UserDto updateEmail(UUID id, EditEmailDto editEmailDto) {
        UserEntity user = getUserById(id);

        user.setEmail(editEmailDto.getEmail());
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    private UserEntity getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Не авторизован.");
                });
    }

}