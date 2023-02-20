package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeekDto {

    private UUID id;

    private int sequenceNumber;

    private Date beginDate;

    private Date endDate;

}
