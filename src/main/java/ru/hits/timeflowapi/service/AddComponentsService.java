package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.mapper.ClassroomMapper;
import ru.hits.timeflowapi.mapper.StudentGroupMapper;
import ru.hits.timeflowapi.mapper.SubjectMapper;
import ru.hits.timeflowapi.mapper.TeacherMapper;
import ru.hits.timeflowapi.model.dto.NewSubjectDto;
import ru.hits.timeflowapi.model.dto.SubjectDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.classroom.NewClassroomDto;
import ru.hits.timeflowapi.model.dto.studentgroup.NewStudentGroupDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.teacher.NewTeacherDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.SubjectEntity;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.repository.ClassroomRepository;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.repository.SubjectRepository;
import ru.hits.timeflowapi.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class AddComponentsService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final StudentGroupRepository studentGroupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final TeacherMapper teacherMapper;
    private final ClassroomMapper classroomMapper;
    private final ClassroomRepository classroomRepository;

    public SubjectDto addSubjects(NewSubjectDto subjectDto) {

        SubjectEntity subjectEntity = subjectMapper.NewSubjectDtoToEntity(subjectDto);
        subjectRepository.save(subjectEntity);
        return new SubjectDto(subjectEntity);
    }

    public StudentGroupBasicDto addGroups(NewStudentGroupDto studentGroupBasicDto) {

        StudentGroupEntity studentGroupEntity = studentGroupMapper.newStudentGroupDtoToEntity(studentGroupBasicDto);
        studentGroupRepository.save(studentGroupEntity);
        return new StudentGroupBasicDto(studentGroupEntity);
    }

    public ClassroomDto addClassrooms(NewClassroomDto classroomDto) {

        ClassroomEntity classroomEntity = classroomMapper.newClassroomDtoToEntity(classroomDto);
        classroomRepository.save(classroomEntity);
        return new ClassroomDto(classroomEntity);
    }

    public TeacherDto addTeachers(NewTeacherDto teacherDto) {

        TeacherEntity teacherEntity = teacherMapper.newTeacherDtoToEntity(teacherDto);
        teacherRepository.save(teacherEntity);
        return new TeacherDto(teacherEntity);
    }
}
