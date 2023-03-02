package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.ResponseBodyMessage;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupTimetableDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherTimetableDto;
import ru.hits.timeflowapi.service.LessonService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
@Tag(name = "Пара")

public class LessonController {

    private final LessonService lessonService;

    @Operation(summary = "Получить пары, которые проходят у группы на неделе.")
    @GetMapping("/group/{groupId}")
    public ResponseEntity<StudentGroupTimetableDto> getWeekLessonsByGroupId(@PathVariable("groupId") UUID groupId,
                                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
        return new ResponseEntity<>(lessonService.getWeekLessonsByGroupId(groupId, startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Получить пары, которые проходят у преподавателя на неделе.")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherTimetableDto> getWeekLessonsByTeacherId(@PathVariable("teacherId") UUID teacherId,
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
        return new ResponseEntity<>(lessonService.getWeekLessonsByTeacherId(teacherId, startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Получить пары, которые проходят в аудитории на неделе.")
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<ClassroomTimetableDto> getWeekLessonsByClassroomId(@PathVariable("classroomId") UUID classroomId,
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
        return new ResponseEntity<>(lessonService.getWeekLessonsByClassroomId(classroomId, startDate, endDate), HttpStatus.OK);
    }
    @Operation(summary = "Получить описание пары.")
    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(lessonService.getLessonById(id), HttpStatus.OK);
    }

    @Operation(summary = "Добавить пару.")
    @PostMapping
    public ResponseEntity<LessonDto> addLesson(@RequestBody @Valid CreateLessonDto createLessonDto) {
        return new ResponseEntity<>(lessonService.addLesson(createLessonDto), HttpStatus.OK);
    }

    @Operation(summary = "Удалить пару.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyMessage> deleteLesson(@PathVariable("id") UUID id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(new ResponseBodyMessage("Пара с ID " + id + " была успешно удалена"), HttpStatus.OK);
    }

    @Operation(summary = "Удалить все пары на неделе.")
    @DeleteMapping
    public ResponseEntity<ResponseBodyMessage> deleteAllLessonsByWeek(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
        lessonService.deleteAllLessonsByWeek(startDate, endDate);
        return new ResponseEntity<>(new ResponseBodyMessage("Пары, которые проходят с " + startDate + " по" + endDate + " успешно удалены"), HttpStatus.OK);
    }

    @Operation(summary = "Обновить данные пары.")
    @PatchMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("id") UUID id, @RequestBody @Valid CreateLessonDto updatedLessonDto) {
        return new ResponseEntity<>(lessonService.updateLesson(id, updatedLessonDto), HttpStatus.OK);
    }

}
