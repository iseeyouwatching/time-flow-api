package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "student_details")
public class StudentDetailsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private String studentNumber;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroupEntity group;

    public StudentDetailsEntity(UserEntity user, String studentNumber, StudentGroupEntity group) {
        this.user = user;
        this.studentNumber = studentNumber;
        this.group = group;
    }

}
