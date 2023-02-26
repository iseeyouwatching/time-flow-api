package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.service.StudentGroupInfoService;

import java.util.List;

@Tag(name = "Группа студентов.")
@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupInfoController {

    private final StudentGroupInfoService studentGroupInfoService;

    @Operation(summary = "Получить список групп студентов.")
    @GetMapping
    public ResponseEntity<List<StudentGroupBasicDto>> getGroups() {
        return new ResponseEntity<>(studentGroupInfoService.getGroups(), HttpStatus.OK);
    }

}