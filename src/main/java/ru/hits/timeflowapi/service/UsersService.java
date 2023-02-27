package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final String property = "name";


    public Page<UserDto> getUsers(int pageNumber,
                                  int pageSize,
                                  Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction);

        Page<UserEntity> users = userRepository.findAll(pageable);

        return users.map(userMapper::userToUserDto);

    }
}