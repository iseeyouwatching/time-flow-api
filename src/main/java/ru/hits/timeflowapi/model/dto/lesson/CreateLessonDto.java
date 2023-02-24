package ru.hits.timeflowapi.model.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLessonDto {

    @NotBlank(message = "Student group ID is required")
    private UUID studentGroupId;

    @NotBlank(message = "Subject ID is required")
    private UUID subjectId;

    @NotBlank(message = "Teacher ID is required")
    private UUID teacherId;

    @NotBlank(message = "Classroom ID is required")
    private UUID classroomId;

    @NotBlank(message = "Timeslot ID is required")
    private UUID timeslotId;

    @NotBlank(message = "Day ID is required")
    private UUID dayId;

    private String lessonType;

}
