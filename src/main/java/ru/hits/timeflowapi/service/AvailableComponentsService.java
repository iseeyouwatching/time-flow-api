package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.model.entity.TimeslotEntity;
import ru.hits.timeflowapi.repository.ClassroomRepository;
import ru.hits.timeflowapi.repository.TeacherRepository;
import ru.hits.timeflowapi.repository.TimeslotRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvailableComponentsService {
    private final TeacherRepository teacherRepository;
    private final TimeslotRepository timeslotRepository;
    private final ClassroomRepository classroomRepository;

    public List<TimeslotDto> getAvailableTimeslots(UUID groupId, LocalDate date) {

        List<TimeslotEntity> timeslots = timeslotRepository.findAll();

        List<TimeslotDto> timeslotDtos = new ArrayList<>();

        for (TimeslotEntity timeslot : timeslots) {
            timeslotDtos.add(new TimeslotDto(timeslot));
        }

        return timeslotDtos;
    }

    public List<ClassroomDto> getAvailableClassrooms(UUID groupId, UUID timeslotId, LocalDate date) {

        List<ClassroomEntity> classrooms = classroomRepository.findAll();

        List<ClassroomDto> classroomDtos = new ArrayList<>();

        for (ClassroomEntity classroom : classrooms) {
            classroomDtos.add(new ClassroomDto(classroom));
        }

        return classroomDtos;
    }

    public List<TeacherDto> getAvailableTeachers(UUID groupId, UUID timeslotId, LocalDate date) {

        List<TeacherEntity> teachers = teacherRepository.findAll();

        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (TeacherEntity teacher : teachers) {
            teacherDtos.add(new TeacherDto(teacher));
        }

        return teacherDtos;
    }
}
