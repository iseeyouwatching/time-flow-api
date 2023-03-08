package ru.hits.timeflowapi.dto.lesson;

import lombok.*;
import ru.hits.timeflowapi.enumeration.LessonType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private LessonType lessonType;

    @NotNull(message = "Количество недель обязательно.")
    @Min(value = 2, message = "Количество недель должно быть больше 1.")
    private int numberOfWeeks;

}
