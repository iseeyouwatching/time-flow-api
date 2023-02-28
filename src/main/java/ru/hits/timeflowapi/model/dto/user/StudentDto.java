package ru.hits.timeflowapi.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {

   private UserDto userInfo;

    private String studentNumber;

    private StudentGroupBasicDto group;

}