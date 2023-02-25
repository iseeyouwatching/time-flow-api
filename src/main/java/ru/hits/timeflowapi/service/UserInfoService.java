package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    public UserDto getUserInfo(String email) {
        UserDto userDto;
        List<UserEntity> users = userRepository.findAll();

        for (UserEntity user : users) {
            if (user.getEmail().equals(email)) {
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
        }
        throw new UnauthorizedException("Не авторизован.");
    }
}
