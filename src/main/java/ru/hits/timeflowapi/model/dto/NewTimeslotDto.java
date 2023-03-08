package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTimeslotDto {
    private int sequenceNumber;

    private String beginTime;

    private String endTime;
}
