package ru.hits.timeflowapi.model.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLessonDto {

    @NotNull(message = "Student group ID is required")
    private UUID studentGroupId;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;

    @NotNull(message = "Teacher ID is required")
    private UUID teacherId;

    @NotNull(message = "Classroom ID is required")
    private UUID classroomId;

    @NotNull(message = "Timeslot ID is required")
    private UUID timeslotId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Lesson type is required")
    @Pattern(regexp = "LECTURE|SEMINAR|PRACTICAL_LESSON|LABORATORY_LESSON|EXAM", message = "Incorrect value. Possible: LECTURE, SEMINAR, PRACTICAL_LESSON, LABORATORY_LESSON, EXAM")
    private String lessonType;

}
