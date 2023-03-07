package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/v1/lessons")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Составление расписания")
public class SchedulingController {

    private final LessonService lessonService;

    @Operation(
            summary = "Добавить пару.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping
    public ResponseEntity<LessonDto> addLesson(@RequestBody @Valid CreateLessonDto createLessonDto) {
        return new ResponseEntity<>(lessonService.addLesson(createLessonDto), HttpStatus.OK);
    }

    @Operation(summary = "Удалить пару.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyMessage> deleteLesson(@PathVariable("id") UUID id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(new ResponseBodyMessage("Пара с ID " + id + " была успешно удалена"), HttpStatus.OK);
    }

    @Operation(summary = "Удалить все пары на неделе у конкретной группы.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/group/{groupId}")
    public ResponseEntity<ResponseBodyMessage> deleteAllLessonsByWeek(@PathVariable("groupId") UUID groupId,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam("startDate") LocalDate startDate,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam("endDate") LocalDate endDate) {
        lessonService.deleteAllLessonsByWeek(groupId, startDate, endDate);
        return new ResponseEntity<>(new ResponseBodyMessage(
                "Пары, которые проходят с " + startDate + " по" + endDate
                        + " у группы с ID " + groupId +" успешно удалены"), HttpStatus.OK
        );
    }

    @Operation(summary = "Обновить данные пары.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("id") UUID id,
                                                  @RequestBody @Valid CreateLessonDto updatedLessonDto) {
        return new ResponseEntity<>(lessonService.updateLesson(id, updatedLessonDto), HttpStatus.OK);
    }

}
