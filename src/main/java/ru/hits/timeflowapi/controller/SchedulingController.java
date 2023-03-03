package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.ResponseBodyMessage;
import ru.hits.timeflowapi.model.dto.lesson.CreateLessonDto;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;
import ru.hits.timeflowapi.service.LessonService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lesson")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Составление расписания")
public class SchedulingController {

    private final LessonService lessonService;

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
    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("id") UUID id, @RequestBody @Valid CreateLessonDto updatedLessonDto) {
        return new ResponseEntity<>(lessonService.updateLesson(id, updatedLessonDto), HttpStatus.OK);
    }

}
