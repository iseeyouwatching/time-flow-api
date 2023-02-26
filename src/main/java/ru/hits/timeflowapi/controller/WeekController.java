package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.WeekDto;
import ru.hits.timeflowapi.service.WeekService;

import java.util.List;

@Tag(name = "Неделя.")
@RestController
@RequestMapping("/api/v1/week")
@RequiredArgsConstructor
public class WeekController {

    private final WeekService weekService;

    @Operation(summary = "Получить список недель.")
    @GetMapping
    public ResponseEntity<List<WeekDto>> getWeeks() {
        return new ResponseEntity<>(weekService.getAllWeeksDtos(), HttpStatus.OK);
    }

}
