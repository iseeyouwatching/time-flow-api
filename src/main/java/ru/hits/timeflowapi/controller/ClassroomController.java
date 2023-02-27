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
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.service.ClassroomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Аудитория.")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Operation(summary = "Получить список аудиторий.")
    @GetMapping
    public ResponseEntity<List<ClassroomDto>> getClassrooms() {
        return new ResponseEntity<>(classroomService.getClassrooms(), HttpStatus.OK);
    }
}
