package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.mapper.*;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.*;
import ru.hits.timeflowapi.repository.*;

@Service
@RequiredArgsConstructor
public class AddComponentsService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final StudentGroupRepository studentGroupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final TeacherMapper teacherMapper;
    private final TimeslotRepository timeslotRepository;
    private final TimeslotMapper timeslotMapper;
    private final ClassroomMapper classroomMapper;
    private final ClassroomRepository classroomRepository;

    public SubjectDto addSubjects(SubjectDto subjectDto) {

        SubjectEntity subjectEntity = subjectMapper.SubjectDtoToEntity(subjectDto);
        subjectRepository.save(subjectEntity);
        return subjectDto;
    }

    public StudentGroupBasicDto addGroups(StudentGroupBasicDto studentGroupBasicDto) {

        StudentGroupEntity studentGroupEntity = studentGroupMapper.StudentGroupDtoToEntity(studentGroupBasicDto);
        studentGroupRepository.save(studentGroupEntity);
        return studentGroupBasicDto;
    }


    public TimeslotDto addTimeslots(TimeslotDto timeslotDto) {

        TimeslotEntity timeslotEntity = timeslotMapper.timeslotDtoToEntity(timeslotDto);
        timeslotRepository.save(timeslotEntity);
        return timeslotDto;
    }

    public ClassroomDto addClassrooms(ClassroomDto classroomDto) {

        ClassroomEntity classroomEntity = classroomMapper.classroomDtoToEntity(classroomDto);
        classroomRepository.save(classroomEntity);
        return classroomDto;
    }

    public TeacherDto addTeachers(TeacherDto teacherDto) {


        TeacherEntity teacherEntity = teacherMapper.TeacherDtoToEntity(teacherDto);
        teacherRepository.save(teacherEntity);
        return teacherDto;
    }
}
