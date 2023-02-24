package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.ResponseBodyMessage;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomTimetableDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonsDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherTimetableDto;
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
    public ResponseEntity<StudentGroupTimetableDto> getLessonsByGroupId(@PathVariable("groupId") UUID id) {
        return new ResponseEntity<>(lessonService.getLessonsByGroupId(id), HttpStatus.OK);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherTimetableDto> getLessonsByTeacherId(@PathVariable("teacherId") UUID id) {
        return new ResponseEntity<>(lessonService.getLessonsByTeacherId(id), HttpStatus.OK);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<ClassroomTimetableDto> getLessonsByClassroomId(@PathVariable("classroomId") UUID id) {
        return new ResponseEntity<>(lessonService.getLessonsByClassroomId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(lessonService.getLessonById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LessonDto> addLesson(@RequestBody CreateLessonDto createLessonDto) {
        return new ResponseEntity<>(lessonService.addLesson(createLessonDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyMessage> deleteLesson(@PathVariable("id") UUID id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(new ResponseBodyMessage("Пара с ID " + id + " была успешно удалена"), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("id") UUID id, @RequestBody CreateLessonDto updatedLessonDto) {
        return new ResponseEntity<>(lessonService.updateLesson(id, updatedLessonDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<LessonsDto> getAllLessons() {
        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.OK);
    }

}
