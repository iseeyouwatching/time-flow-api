package ru.hits.timeflowapi.model.entity.requestconfirm;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "student_request_confirm")
public class StudentRequestConfirmEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @OneToOne
    private StudentDetailsEntity studentDetails;

    private boolean isCompleted;

}
