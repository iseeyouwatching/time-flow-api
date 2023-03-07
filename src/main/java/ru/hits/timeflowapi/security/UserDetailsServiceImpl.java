package ru.hits.timeflowapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.entity.EmployeePostEntity;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.enumeration.Role;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(UUID.fromString(id));

        if (user.isPresent()) {
            List<String> postRoles = new ArrayList<>();

            if (user.get().getRole() == Role.ROLE_EMPLOYEE) {
                postRoles = getPostRoles(user.get().getEmployee());
            }

            return new UserDetailsImpl(user.get(), postRoles);
        }

        throw new UnauthorizedException("Не авторизован.");
    }

    private List<String> getPostRoles(EmployeeDetailsEntity employeeDetails) {
        List<String> posts = new ArrayList<>();

        for (EmployeePostEntity post : employeeDetails.getPosts()) {
            posts.add(post.getPostRole());

        }

        return posts;
    }

}