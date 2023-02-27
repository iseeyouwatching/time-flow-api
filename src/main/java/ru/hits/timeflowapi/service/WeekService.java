package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.WeekDto;
import ru.hits.timeflowapi.model.entity.WeekEntity;
import ru.hits.timeflowapi.repository.WeekRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;

    public List<WeekEntity> getAllWeeks() {
        return weekRepository.findAll();
    }

    public List<WeekDto> getAllWeeksDtos() {

        List<WeekEntity> weeks = weekRepository.findAll();

        List<WeekDto> weekDtos = new ArrayList<>();

        for (WeekEntity week: weeks) {
            weekDtos.add(new WeekDto(week));
        }

        return weekDtos;

    }

}
