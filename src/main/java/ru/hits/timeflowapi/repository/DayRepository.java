package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.DayEntity;
import ru.hits.timeflowapi.model.entity.WeekEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface DayRepository extends JpaRepository<DayEntity, UUID> {

    List<DayEntity> findByWeek(WeekEntity week);

}