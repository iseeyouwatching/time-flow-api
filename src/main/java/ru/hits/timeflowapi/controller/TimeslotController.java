package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.service.TimeslotService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timeslot")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Таймслот")
public class TimeslotController {

    private final TimeslotService timeslotService;

    @Operation(summary = "Получить список таймслотов.")
    @GetMapping
    public ResponseEntity<List<TimeslotDto>> getTimeslots() {
        return new ResponseEntity<>(timeslotService.getTimeslots(), HttpStatus.OK);
    }

}
