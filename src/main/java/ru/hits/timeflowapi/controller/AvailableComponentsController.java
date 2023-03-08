package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.dto.TimeslotDto;
import ru.hits.timeflowapi.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.AvailableComponentsService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Доступные компоненты пары")
public class AvailableComponentsController {
    private final AvailableComponentsService availableComponentsService;

    @Operation(
            summary = "Получить список доступных таймслотов.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/available-timeslots/{groupId}")
    public List<TimeslotDto> getAvailableTimeslots(
            @RequestBody @PathVariable("groupId") UUID groupId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam("date") LocalDate date
    ) {
        return availableComponentsService.getAvailableTimeslots(groupId, date);
    }

    @Operation(
            summary = "Получить список доступных аудиторий.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/available-classrooms/{timeslotId}")
    public List<ClassroomDto> getAvailableClassrooms(
            @RequestBody @PathVariable("timeslotId") UUID timeslotId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam("date") LocalDate date) {
        return availableComponentsService.getAvailableClassrooms(timeslotId, date);
    }

    @Operation(
            summary = "Получить список доступных преподавателей.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/available-teachers/{timeslotId}")
    public List<TeacherDto> getAvailableTeachers(
            @RequestBody @PathVariable("timeslotId") UUID timeslotId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam("date") LocalDate date) {
        return availableComponentsService.getAvailableTeachers(timeslotId, date);
    }

}
