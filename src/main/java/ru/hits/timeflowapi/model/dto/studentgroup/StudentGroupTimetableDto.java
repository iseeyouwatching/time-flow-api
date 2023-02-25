package ru.hits.timeflowapi.model.dto.studentgroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupTimetableDto {

    private StudentGroupBasicDto studentGroup;

    private UUID weekId;

    private List<LessonDto> lessons;

}
