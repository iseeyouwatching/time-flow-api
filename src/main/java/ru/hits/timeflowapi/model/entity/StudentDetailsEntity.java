package ru.hits.timeflowapi.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "student")

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

}
