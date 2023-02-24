package ru.hits.timeflowapi.model.dto.day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.entity.DayEntity;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayDto {

    private UUID id;

    private LocalDate date;

    public DayDto(DayEntity entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
    }

}
