package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.dto.day.DayDto;
import ru.hits.timeflowapi.model.dto.day.DaysDto;
import ru.hits.timeflowapi.model.entity.DayEntity;
import ru.hits.timeflowapi.model.entity.WeekEntity;
import ru.hits.timeflowapi.repository.DayRepository;
import ru.hits.timeflowapi.repository.WeekRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;
    private final WeekRepository weekRepository;

    public DaysDto getDaysByWeekId(UUID weekId) {

        WeekEntity week = weekRepository.findById(weekId).orElse(null);

        if (week == null) {
            throw new NotFoundException("Недели с таким ID " + weekId + " не существует");
        }

        List<DayEntity> days = dayRepository.findByWeek(week);

        List<DayDto> dayDtos = new ArrayList<>();

        for (DayEntity day: days) {
            dayDtos.add(new DayDto(day.getId(), day.getDate()));
        }

        return new DaysDto(dayDtos);

    }


}
