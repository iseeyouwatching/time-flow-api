package ru.hits.timeflowapi.controller.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.AddComponentsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Добавление новых компонентов")
public class AddComponentsController {
    private final AddComponentsService addComponentsService;

    @Operation(summary = "Добавить новый предмет.")
    @PostMapping("/subjects")
    public SubjectDto addSubjects(@RequestBody @Valid SubjectDto subjectDto) {
        return addComponentsService.addSubjects(subjectDto);
    }

    @Operation(summary = "Добавить новую группу студентов.")
    @PostMapping("/groups")
    public StudentGroupBasicDto addGroups(@RequestBody @Valid StudentGroupBasicDto studentGroupBasicDto) {
        return addComponentsService.addGroups(studentGroupBasicDto);
    }

    @Operation(summary = "Добавить новый таймслот.")
    @PostMapping("/timeslots")
    public TimeslotDto addTimeslots(@RequestBody @Valid TimeslotDto timeslotDto) {
        return addComponentsService.addTimeslots(timeslotDto);
    }

    @Operation(summary = "Добавить новую аудиторию.")
    @PostMapping("/classrooms")
    public ClassroomDto addClassrooms(@RequestBody @Valid ClassroomDto classroomDto) {
        return addComponentsService.addClassrooms(classroomDto);
    }

    @Operation(summary = "Добавить нового преподавателя.")
    @PostMapping("/teachers")
    public TeacherDto addTeachers(@RequestBody @Valid TeacherDto teacherDto) {
        return addComponentsService.addTeachers(teacherDto);
    }

}
