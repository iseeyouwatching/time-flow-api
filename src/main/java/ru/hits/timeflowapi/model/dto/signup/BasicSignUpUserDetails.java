package ru.hits.timeflowapi.model.dto.signup;

import ru.hits.timeflowapi.model.enumeration.Sex;

/**
 * Интерфейс для DTO, которые включают в себя базовую информацию о пользователе.
 */
public interface BasicSignUpUserDetails {

    String getEmail();

    String getName();

    String getSurname();

    String getPatronymic();

    Sex getSex();

    String getPassword();

    void setEmail(String email);

    void setName(String name);

    void setSurname(String surname);

    void setPatronymic(String patronymic);

    void setSex(Sex sex);

    void setPassword(String password);

}
