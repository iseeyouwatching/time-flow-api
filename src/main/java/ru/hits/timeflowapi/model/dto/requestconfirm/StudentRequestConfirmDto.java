package ru.hits.timeflowapi.model.dto.requestconfirm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.user.StudentDto;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestConfirmDto {

    private UUID id;

    private Date creationDate;

    private Date closedDate;

    private boolean isCompleted;

    private StudentDto studentDto;

}
