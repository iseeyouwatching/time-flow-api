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
@Table(name = "employee_post")
public class EmployeePostEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(unique = true)
    private String postRole;    // пример: "ROLE_ADMIN"

    @Column(unique = true)
    private String postName;    // пример: "Администратор"

    @ManyToMany(mappedBy = "posts")
    private List<EmployeeDetailsEntity> employees;

}
