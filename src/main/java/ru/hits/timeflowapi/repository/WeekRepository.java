package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.WeekEntity;

import java.util.UUID;

@Repository
public interface WeekRepository extends JpaRepository<WeekEntity, UUID> {

    WeekEntity findBySequenceNumber(Integer page);

}
