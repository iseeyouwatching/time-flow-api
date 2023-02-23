package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.entity.TeacherEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private UUID id;

    private String name;

    private String surname;

    private String patronymic;

    public TeacherDto(TeacherEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.patronymic = entity.getPatronymic();
    }

}
