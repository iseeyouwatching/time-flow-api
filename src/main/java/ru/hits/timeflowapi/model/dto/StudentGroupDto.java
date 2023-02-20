package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupDto {

    private UUID id;

    private int number;

}
