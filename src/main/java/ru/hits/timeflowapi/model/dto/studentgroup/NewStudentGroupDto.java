package ru.hits.timeflowapi.model.dto.studentgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewStudentGroupDto {
    @Schema(description = "Номер учебной группы", example = "972102")
    @NotBlank(message = "Номер группы не может быть пустым.")
    private int number;

}
