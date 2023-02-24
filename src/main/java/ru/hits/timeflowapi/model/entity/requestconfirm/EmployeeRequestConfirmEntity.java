package ru.hits.timeflowapi.model.entity.requestconfirm;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "employee_maker_request_confirm")
public class EmployeeRequestConfirmEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @OneToOne
    private EmployeeDetailsEntity employeeDetails;

    private boolean isCompleted;

}
