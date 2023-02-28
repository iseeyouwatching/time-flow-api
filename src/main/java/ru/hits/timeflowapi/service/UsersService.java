package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public List<UserDto> getUsers() {
        List<UserEntity> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();

        for (UserEntity user : users) {
            userDtos.add(new UserDto(
                    user.getId(),
                    user.getEmail(),
                    user.getRole(),
                    user.getName(),
                    user.getSurname(),
                    user.getPatronymic(),
                    user.getAccountStatus(),
                    user.getSex()));
        }

        return userDtos;
    }
}
