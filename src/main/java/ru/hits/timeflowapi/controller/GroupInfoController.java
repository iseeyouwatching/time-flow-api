package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.service.StudentGroupInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupInfoController {

    private final StudentGroupInfoService studentGroupInfoService;

    @GetMapping
    List<StudentGroupBasicDto> getGroups() {
        return studentGroupInfoService.getGroups();
    }

}