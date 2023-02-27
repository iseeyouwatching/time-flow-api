package ru.hits.timeflowapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserEntity user;
    private final List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        addPostsIntoAuthorities(authorities);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Добавляет роли сотрудника в {@code authorities} тогда, когда пользователь является сотрудником,
     * а его аккаунт активирован.
     *
     * @param authorities полномочия.
     */
    private void addPostsIntoAuthorities(List<SimpleGrantedAuthority> authorities) {
        if (user.getRole() == Role.ROLE_EMPLOYEE && user.getAccountStatus() == AccountStatus.ACTIVATE) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
    }

}