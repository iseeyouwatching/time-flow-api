package ru.hits.timeflowapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);

        if (user.isPresent()) {
            List<String> postRoles = new ArrayList<>();

            if (user.get().getRole() == Role.ROLE_EMPLOYEE) {
                postRoles = getPostRoles(user.get().getEmployee());
            }

            return new UserDetailsImpl(user.get(), postRoles);
        }

        throw new UsernameNotFoundException("Пользователь с такой почтой не найден");
    }

    private List<String> getPostRoles(EmployeeDetailsEntity employeeDetails) {
        List<String> posts = new ArrayList<>();

        for (EmployeePostEntity post : employeeDetails.getPosts()) {
            posts.add(post.getPostRole());

        }

        return posts;
    }

}