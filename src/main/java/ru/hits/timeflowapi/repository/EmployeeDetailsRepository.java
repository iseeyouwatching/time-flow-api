package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetailsEntity, UUID> {

    boolean existsByContractNumber(String contractNumber);

    Optional<EmployeeDetailsEntity> findByUserId(UUID userId);

}
