package ru.hits.timeflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.entity.TimeslotEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeslotDto {

    private UUID id;

    private int sequenceNumber;

    private String beginTime;

    private String endTime;

    public TimeslotDto(TimeslotEntity entity) {
        this.id = entity.getId();
        this.sequenceNumber = entity.getSequenceNumber();
        this.beginTime = entity.getBeginTime();
        this.endTime = entity.getEndTime();
    }

}
