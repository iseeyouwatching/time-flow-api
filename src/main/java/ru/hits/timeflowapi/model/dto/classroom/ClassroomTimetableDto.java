package ru.hits.timeflowapi.model.dto.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.lesson.LessonDto;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomTimetableDto {

    private ClassroomDto classroom;

    private UUID weekId;

    private List<LessonDto> lessons;

}
