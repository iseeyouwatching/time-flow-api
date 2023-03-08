package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.entity.StudentGroupEntity;

import java.util.UUID;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroupEntity, UUID> {
}
