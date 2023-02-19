package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "student_group")
public class StudentGroupEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(unique = true)
    private int number;

    @OneToMany(mappedBy = "studentGroup")
    private List<LessonEntity> lesson;

    @OneToMany(mappedBy = "group")
    private List<StudentDetailsEntity> students;

    public StudentGroupEntity(int number) {
        this.number = number;
    }

}
