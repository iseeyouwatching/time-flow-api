package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.repository.ClassroomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public List<ClassroomDto> getClassrooms() {

        List<ClassroomEntity> classrooms = classroomRepository.findAll();

        List<ClassroomDto> classroomDtos = new ArrayList<>();

        for (ClassroomEntity classroom: classrooms) {
            classroomDtos.add(new ClassroomDto(classroom));
        }

        return classroomDtos;

    }
}
