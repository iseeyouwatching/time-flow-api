package ru.hits.timeflowapi.model.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.*;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.LessonEntity;
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

    private DayDto day;

    private LessonType lessonType;

    public LessonDto(LessonEntity lesson) {
        this.id = lesson.getId();
        this.studentGroup = new StudentGroupBasicDto(lesson.getStudentGroup());
        this.subject = new SubjectDto(lesson.getSubject());
        this.teacher = new TeacherDto(lesson.getTeacher());
        this.classroom = new ClassroomDto(lesson.getClassroom());
        this.timeslot = new TimeslotDto(lesson.getTimeslot());
        this.week = new WeekDto(lesson.getWeek());
        this.day = new DayDto(lesson.getDay());
        this.lessonType = lesson.getLessonType();
    }

}
