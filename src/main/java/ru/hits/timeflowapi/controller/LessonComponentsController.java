package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.LessonComponentsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson/")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Составляющие пары")
public class LessonComponentsController {

    private final LessonComponentsService lessonComponentsService;

    @Operation(summary = "Получить список предметов.")
    @GetMapping("subject")
    public List<SubjectDto> getSubjects() {
        return lessonComponentsService.getSubjects();
    }

    @Operation(summary = "Получить список групп студентов.")
    @GetMapping("student")
    public List<StudentGroupBasicDto> getGroups() {
        return lessonComponentsService.getGroups();
    }

    @Operation(summary = "Получить список таймслотов.")
    @GetMapping("timeslot")
    public List<TimeslotDto> getTimeslots() {
        return lessonComponentsService.getTimeslots();
    }

    @Operation(summary = "Получить список аудиторий.")
    @GetMapping("classroom")
    public List<ClassroomDto> getClassrooms() {
        return lessonComponentsService.getClassrooms();
    }

    @Operation(summary = "Получить список преподавателей.")
    @GetMapping("teacher")
    public List<TeacherDto> getTeachers() {
        return lessonComponentsService.getTeachers();
    }
}
