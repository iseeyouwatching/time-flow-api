package ru.hits.timeflowapi.model.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLessonForAFewWeeksDto {

    @NotNull(message = "Уникальный идентификатор группы студентов обязателен.")
    private UUID studentGroupId;

    @NotNull(message = "Уникальный идентификатор предмета обязателен.")
    private UUID subjectId;

    @NotNull(message = "Уникальный идентификатор преподавателя обязателен.")
    private UUID teacherId;

    @NotNull(message = "Уникальный идентификатор аудитории обязателен.")
    private UUID classroomId;

    @NotNull(message = "Уникальный идентификатор таймслота обязателен.")
    private UUID timeslotId;

    @NotNull(message = "Дата обязательна.")
    private LocalDate date;

    @NotNull(message = "Тип пары обязателен.")
    @Pattern(regexp = "LECTURE|SEMINAR|PRACTICAL_LESSON|LABORATORY_LESSON|EXAM",
            message = "Некорректное значение. Возможные: LECTURE, SEMINAR, PRACTICAL_LESSON, LABORATORY_LESSON, EXAM")
    private String lessonType;

    @NotNull(message = "Количество недель обязательно.")
    @Min(value = 2, message = "Количество недель должно быть больше 2.")
    private int numberOfWeeks;

}
