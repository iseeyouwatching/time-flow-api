package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.dto.SubjectDto;
import ru.hits.timeflowapi.dto.TimeslotDto;
import ru.hits.timeflowapi.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.entity.*;
import ru.hits.timeflowapi.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonComponentsService {

    private final SubjectRepository subjectRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final TeacherRepository teacherRepository;
    private final TimeslotRepository timeslotRepository;
    private final ClassroomRepository classroomRepository;

    public List<SubjectDto> getSubjects() {

        List<SubjectEntity> subjects = subjectRepository.findAll();

        List<SubjectDto> subjectDtos = new ArrayList<>();

        for (SubjectEntity subject : subjects) {
            subjectDtos.add(new SubjectDto(subject));
        }

        return subjectDtos;
    }

    public List<StudentGroupBasicDto> getGroups() {
        List<StudentGroupEntity> studentGroups = studentGroupRepository.findAll();

        List<StudentGroupBasicDto> studentGroupBasicDtos = new ArrayList<>();

        for (StudentGroupEntity studentGroup : studentGroups) {
            studentGroupBasicDtos.add(new StudentGroupBasicDto(studentGroup.getId(), studentGroup.getNumber()));
        }

        return studentGroupBasicDtos;
    }

    public StudentGroupEntity getGroupEntityById(UUID id) {
        return studentGroupRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Группа с таким ID не найдена");
                });
    }

    public List<TimeslotDto> getTimeslots() {

        List<TimeslotEntity> timeslots = timeslotRepository.findAll();

        List<TimeslotDto> timeslotDtos = new ArrayList<>();

        for (TimeslotEntity timeslot : timeslots) {
            timeslotDtos.add(new TimeslotDto(timeslot));
        }

        return timeslotDtos;
    }

    public List<ClassroomDto> getClassrooms() {

        List<ClassroomEntity> classrooms = classroomRepository.findAll();

        List<ClassroomDto> classroomDtos = new ArrayList<>();

        for (ClassroomEntity classroom : classrooms) {
            classroomDtos.add(new ClassroomDto(classroom));
        }

        return classroomDtos;
    }

    public List<TeacherDto> getTeachers() {

        List<TeacherEntity> teachers = teacherRepository.findAll();

        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (TeacherEntity teacher : teachers) {
            teacherDtos.add(new TeacherDto(teacher));
        }

        return teacherDtos;
    }
}
