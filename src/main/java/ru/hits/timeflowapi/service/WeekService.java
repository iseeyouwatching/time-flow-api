package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.entity.WeekEntity;
import ru.hits.timeflowapi.repository.WeekRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;

    public List<WeekEntity> getAllWeeks() {
        return weekRepository.findAll();
    }

}
