package ru.hits.timeflowapi.dto.studentgroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.entity.StudentGroupEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentGroupBasicDto {

    private UUID id;

    private int number;

    public StudentGroupBasicDto(StudentGroupEntity entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
    }

}