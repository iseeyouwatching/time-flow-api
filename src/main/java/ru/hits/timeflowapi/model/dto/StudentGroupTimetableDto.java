package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupTimetableDto {

    private StudentGroupDto studentGroup;

    private List<LessonDto> lessons;

}
