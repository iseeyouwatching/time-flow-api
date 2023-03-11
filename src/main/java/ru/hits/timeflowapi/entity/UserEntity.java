package ru.hits.timeflowapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.enumeration.AccountStatus;
import ru.hits.timeflowapi.enumeration.Role;
import ru.hits.timeflowapi.enumeration.Sex;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "_user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String email;

    private boolean isEmailConfirmed;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    private String surname;

    private String patronymic;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(mappedBy = "user")
    private StudentDetailsEntity student;

    @OneToOne(mappedBy = "user")
    private EmployeeDetailsEntity employee;

    private String refreshToken;

}
