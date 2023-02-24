package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.entity.WeekEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeekDto {

    private UUID id;

    private int sequenceNumber;

    private LocalDate beginDate;

    private LocalDate endDate;

    public WeekDto(WeekEntity entity) {
        this.id = entity.getId();
        this.sequenceNumber = entity.getSequenceNumber();
        this.beginDate = entity.getBeginDate();
        this.endDate = entity.getEndDate();
    }

}
