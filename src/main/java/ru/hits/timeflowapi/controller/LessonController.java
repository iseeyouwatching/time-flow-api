package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.service.LessonService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/group/{id}")
    public StudentGroupTimetableDto getLessonsByGroup(@PathVariable("id") UUID id) {
        return lessonService.getLessonsByGroup(id);
    }

    @PostMapping
    public void addLesson(@RequestBody CreateLessonDto createLessonDto) {
        lessonService.addLesson(createLessonDto);
    }

}
