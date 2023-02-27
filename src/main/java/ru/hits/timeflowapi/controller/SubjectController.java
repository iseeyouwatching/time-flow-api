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
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Предмет")
public class SubjectController {

    private final SubjectService subjectService;

    @Operation(summary = "Получить список предметов.")
    @GetMapping
    public ResponseEntity<List<SubjectDto>> getTeachers() {
        return new ResponseEntity<>(subjectService.getSubjects(), HttpStatus.OK);
    }

}
