package ru.hits.timeflowapi.dto.studentgroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.dto.lesson.LessonDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupTimetableDto {

    private StudentGroupBasicDto studentGroup;

    private List<LessonDto> lessons;

}
