package ru.hits.timeflowapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.dto.user.StudentDto;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDto {

    private UUID id;

    private Date creationDate;

    private Date closedDate;

    private boolean isClosed;

    private StudentDto student;

}
