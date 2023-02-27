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
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Преподаватель.")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Получить список преподавателей.")
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return new ResponseEntity<>(teacherService.getTeachers(), HttpStatus.OK);
    }

}
