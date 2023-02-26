package ru.hits.timeflowapi.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.model.enumeration.Sex;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {

    private UUID userId;

    private String email;

    private Role role;

    private String name;

    private String surname;

    private String patronymic;

    private AccountStatus accountStatus;

    private Sex sex;

    private String studentNumber;

    private StudentGroupBasicDto group;

}