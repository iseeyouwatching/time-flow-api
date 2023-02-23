package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.TeacherTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.service.LessonService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/group/{groupId}")
    public StudentGroupTimetableDto getLessonsByGroupId(@PathVariable("groupId") UUID id) {
        return lessonService.getLessonsByGroupId(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherTimetableDto getLessonsByTeacherId(@PathVariable("teacherId") UUID id) {
        return lessonService.getLessonsByTeacherId(id);
    }

    @PostMapping
    public void addLesson(@RequestBody CreateLessonDto createLessonDto) {
        lessonService.addLesson(createLessonDto);
    }

}
