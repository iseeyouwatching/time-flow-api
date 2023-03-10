package ru.hits.timeflowapi.controller.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.NewSubjectDto;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.NewClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.NewStudentGroupDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.NewTeacherDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.service.AddComponentsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Добавление новых компонентов")
public class AddComponentsController {
    private final AddComponentsService addComponentsService;

    @Operation(
            summary = "Добавить новый предмет.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/subjects")
    public SubjectDto addSubjects(@RequestBody @Valid NewSubjectDto subjectDto) {
        return addComponentsService.addSubjects(subjectDto);
    }

    @Operation(
            summary = "Добавить новую группу студентов.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/groups")
    public StudentGroupBasicDto addGroups(@RequestBody @Valid NewStudentGroupDto studentGroupBasicDto) {
        return addComponentsService.addGroups(studentGroupBasicDto);
    }

    @Operation(
            summary = "Добавить новую аудиторию.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/classrooms")
    public ClassroomDto addClassrooms(@RequestBody @Valid NewClassroomDto classroomDto) {
        return addComponentsService.addClassrooms(classroomDto);
    }

    @Operation(
            summary = "Добавить нового преподавателя.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/teachers")
    public TeacherDto addTeachers(@RequestBody @Valid NewTeacherDto teacherDto) {
        return addComponentsService.addTeachers(teacherDto);
    }

}
