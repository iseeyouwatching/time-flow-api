package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.AvailableComponentsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/available/")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Доступные компоненты пары")
public class AvailableСomponentsController {
    private final AvailableComponentsService availableComponentsService;

    @Operation(summary = "Получить список доступных таймслотов.")
    @GetMapping("timeslot")
    public List<TimeslotDto> getTimeslots() {
        return availableComponentsService.getAvailableTimeslots();
    }

    @Operation(summary = "Получить список доступных аудиторий.")
    @GetMapping("classroom")
    public List<ClassroomDto> getClassrooms() {
        return availableComponentsService.getAvailableClassrooms();
    }

    @Operation(summary = "Получить список доступных преподавателей.")
    @GetMapping("teacher")
    public List<TeacherDto> getTeachers() {
        return availableComponentsService.getAvailableTeachers();
    }

}
