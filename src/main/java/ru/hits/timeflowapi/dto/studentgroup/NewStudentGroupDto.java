package ru.hits.timeflowapi.dto.studentgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewStudentGroupDto {
    @Schema(description = "Номер учебной группы", example = "972102")
    @NotNull(message = "Номер учебной группы не может быть пустым.")
    private Integer number;

}
