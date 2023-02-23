package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.model.enumeration.LessonType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "lesson")
public class LessonEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_group_id", nullable = false)
    private StudentGroupEntity studentGroup;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassroomEntity classroom;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private TimeslotEntity timeslot;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private DayEntity day;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_type")
    private LessonType lessonType;

}
