package ru.hits.timeflowapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.entity.SubjectEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private UUID id;

    private String name;

    public SubjectDto(SubjectEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

}
