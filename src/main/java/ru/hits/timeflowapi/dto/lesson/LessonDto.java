package ru.hits.timeflowapi.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.dto.SubjectDto;
import ru.hits.timeflowapi.dto.TimeslotDto;
import ru.hits.timeflowapi.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.entity.LessonEntity;
import ru.hits.timeflowapi.enumeration.LessonType;

import java.time.LocalDate;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private LessonType lessonType;

    public LessonDto(LessonEntity lesson) {
        this.id = lesson.getId();
        this.studentGroup = new StudentGroupBasicDto(lesson.getStudentGroup());
        this.subject = new SubjectDto(lesson.getSubject());
        this.teacher = new TeacherDto(lesson.getTeacher());
        this.classroom = new ClassroomDto(lesson.getClassroom());
        this.timeslot = new TimeslotDto(lesson.getTimeslot());
        this.date = lesson.getDate();
        this.lessonType = lesson.getLessonType();
    }

}
