package ru.hits.timeflowapi.model.dto.studentgroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentGroupBasicDto {

    private UUID id;

    private int groupNumber;

}