package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.YearEntity;

import java.util.UUID;

@Repository
public interface YearRepository extends JpaRepository<YearEntity, UUID> {
}
