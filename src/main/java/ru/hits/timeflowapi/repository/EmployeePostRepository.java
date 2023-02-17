package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;

import java.util.UUID;

@Repository
public interface EmployeePostRepository extends JpaRepository<EmployeePostEntity, UUID> {
}
