package ru.hits.timeflowapi.model.dto.requestconfirm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequestConfirmDto {

    private UUID id;

    private Date creationDate;

    private Date closedDate;

    private boolean isClosed;

    private EmployeeDto employee;

}
