package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.entity.TimeslotEntity;

import java.util.UUID;

@Repository
public interface TimeslotRepository extends JpaRepository<TimeslotEntity, UUID> {
}
