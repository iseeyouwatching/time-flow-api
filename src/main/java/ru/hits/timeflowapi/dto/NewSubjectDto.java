package ru.hits.timeflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSubjectDto {
    @NotEmpty(message = "Название предмета не может быть пустым.")
    private String name;
}
