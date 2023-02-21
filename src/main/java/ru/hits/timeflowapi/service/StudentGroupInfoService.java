package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.repository.StudentGroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupInfoService {

    private final StudentGroupRepository studentGroupRepository;

    public List<StudentGroupBasicDto> getGroups() {
        List<StudentGroupEntity> studentGroups = studentGroupRepository.findAll();

        List<StudentGroupBasicDto> studentGroupBasicDtos = new ArrayList<>();

        for (StudentGroupEntity studentGroup : studentGroups) {
            studentGroupBasicDtos.add(new StudentGroupBasicDto(studentGroup.getId(), studentGroup.getNumber()));
        }

        return studentGroupBasicDtos;
    }

}