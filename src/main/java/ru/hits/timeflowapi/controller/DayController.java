package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.day.DaysDto;
import ru.hits.timeflowapi.service.DayService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @GetMapping("/week/{weekId}")
    public ResponseEntity<DaysDto> getDaysByWeekId(@PathVariable("weekId") UUID weekId) {
        return new ResponseEntity<>(dayService.getDaysByWeekId(weekId), HttpStatus.OK);
    }

}
