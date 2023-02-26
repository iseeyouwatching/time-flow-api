package ru.hits.timeflowapi.model.dto.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDto {

    private UUID id;

    private String number;

    public ClassroomDto(ClassroomEntity entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
    }

}
