package ru.hits.timeflowapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.enumeration.AccountStatus;
import ru.hits.timeflowapi.enumeration.Role;
import ru.hits.timeflowapi.enumeration.Sex;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private UUID id;

    private String email;

    private Role role;

    private String name;

    private String surname;

    private String patronymic;

    private AccountStatus accountStatus;

    private Sex sex;

}