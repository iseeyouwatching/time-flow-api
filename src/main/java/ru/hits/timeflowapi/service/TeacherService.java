package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDto> getTeachers() {

        List<TeacherEntity> teachers = teacherRepository.findAll();

        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (TeacherEntity teacher: teachers) {
            teacherDtos.add(new TeacherDto(teacher));
        }

        return teacherDtos;

    }

}
