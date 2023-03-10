package ru.hits.timeflowapi.model.dto.classroom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewClassroomDto {

    @Schema(description = "Номер аудитории", example = "320 аудитория")
    @NotBlank(message = "Номер аудитории не может быть пустым.")
    private String number;

}
