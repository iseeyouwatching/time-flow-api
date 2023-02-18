package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.model.enumeration.Sex;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String email;

    private Role role;

    private String name;
    private String surname;
    private String patronymic;
    private AccountStatus accountStatus;
    private String password;
    private Sex sex;

    @OneToOne(mappedBy = "user")
    private StudentDetailsEntity student;

    @OneToOne(mappedBy = "user")
    private EmployeeDetailsEntity employee;

}
