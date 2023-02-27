package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.entity.TimeslotEntity;
import ru.hits.timeflowapi.repository.TimeslotRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeslotService {

    private final TimeslotRepository timeslotRepository;

    public List<TimeslotDto> getTimeslots() {

        List<TimeslotEntity> timeslots = timeslotRepository.findAll();

        List<TimeslotDto> timeslotDtos = new ArrayList<>();

        for (TimeslotEntity timeslot: timeslots) {
            timeslotDtos.add(new TimeslotDto(timeslot));
        }

        return timeslotDtos;

    }

}
