package ru.hits.timeflowapi.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.model.enumeration.Sex;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private UUID id;

    private String email;

    private boolean isEmailConfirmed;

    private Role role;

    private String name;

    private String surname;

    private String patronymic;

    private AccountStatus accountStatus;

    private Sex sex;

}