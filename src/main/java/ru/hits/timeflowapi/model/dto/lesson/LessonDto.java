package ru.hits.timeflowapi.model.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.*;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.enumeration.LessonType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {

    private UUID id;

    private StudentGroupBasicDto studentGroup;

    private SubjectDto subject;

    private TeacherDto teacher;

    private ClassroomDto classroom;

    private TimeslotDto timeslot;

    private WeekDto week;

    private LessonType lessonType;

}
