package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetailsEntity, UUID> {

    boolean existsByStudentNumber(String studentNumber);

    Optional<StudentDetailsEntity> findByUserId(UUID userId);


}
