package ru.hits.timeflowapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.model.enumeration.Sex;

import javax.persistence.*;
import java.util.UUID;

@Entity
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

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(mappedBy = "user")
    private StudentDetailsEntity student;

    @OneToOne(mappedBy = "user")
    private EmployeeDetailsEntity employee;

    public UserEntity(
            String email,
            Role role,
            String name,
            String surname,
            String patronymic,
            AccountStatus accountStatus,
            String password,
            Sex sex
    ) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.accountStatus = accountStatus;
        this.password = password;
        this.sex = sex;
    }
}
