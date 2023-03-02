package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.AvailableComponentsService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/available/")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Доступные компоненты пары")
public class AvailableСomponentsController {
    private final AvailableComponentsService availableComponentsService;

    @Operation(summary = "Получить список доступных таймслотов.")
    @GetMapping("timeslot/{groupId}")
    public List<TimeslotDto> getAvailableTimeslots
            (@PathVariable("groupId") UUID groupId,
             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
             @RequestParam("date") LocalDate date) {
        return availableComponentsService.getAvailableTimeslots(groupId, date);
    }

    @Operation(summary = "Получить список доступных аудиторий.")
    @GetMapping("classroom/{timeslotId}")
    public List<ClassroomDto> getAvailableClassrooms(@PathVariable("timeslotId") UUID timeslotId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @RequestParam("date") LocalDate date) {
        return availableComponentsService.getAvailableClassrooms(timeslotId, date);
    }

    @Operation(summary = "Получить список доступных преподавателей.")
    @GetMapping("teacher/{timeslotId}")
    public List<TeacherDto> getAvailableTeachers(@RequestBody @PathVariable("timeslotId") UUID timeslotId,
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                 @RequestParam("date") LocalDate date) {
        return availableComponentsService.getAvailableTeachers(timeslotId, date);
    }

}
