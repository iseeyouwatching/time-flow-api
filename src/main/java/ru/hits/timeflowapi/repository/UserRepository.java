package ru.hits.timeflowapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByIdAndRefreshToken(UUID uuid, String refreshToken);

    Page<UserEntity> findAllByRole(Pageable pageable, Role role);

    boolean existsByEmail(String email);

    boolean existsByIdAndRefreshToken(UUID id, String refreshToken);

}
