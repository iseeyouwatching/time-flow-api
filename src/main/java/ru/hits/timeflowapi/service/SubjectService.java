package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.entity.SubjectEntity;
import ru.hits.timeflowapi.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<SubjectDto> getSubjects() {

        List<SubjectEntity> subjects = subjectRepository.findAll();

        List<SubjectDto> subjectDtos = new ArrayList<>();

        for (SubjectEntity subject: subjects) {
            subjectDtos.add(new SubjectDto(subject));
        }

        return subjectDtos;

    }

}
