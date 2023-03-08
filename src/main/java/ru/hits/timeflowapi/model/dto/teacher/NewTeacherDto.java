package ru.hits.timeflowapi.model.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTeacherDto {
    private String name;

    private String surname;

    private String patronymic;
}
