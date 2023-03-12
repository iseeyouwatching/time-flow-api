package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.entity.EmployeePostEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeePostRepository extends JpaRepository<EmployeePostEntity, UUID> {

    Optional<EmployeePostEntity> findByPostRole(String postRole);

    boolean existsByPostName(String postName);

    boolean existsByPostRole(String postRole);

}
