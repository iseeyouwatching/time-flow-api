package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.dto.NewSubjectDto;
import ru.hits.timeflowapi.dto.SubjectDto;
import ru.hits.timeflowapi.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.dto.classroom.NewClassroomDto;
import ru.hits.timeflowapi.dto.studentgroup.NewStudentGroupDto;
import ru.hits.timeflowapi.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.dto.teacher.NewTeacherDto;
import ru.hits.timeflowapi.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.entity.ClassroomEntity;
import ru.hits.timeflowapi.entity.StudentGroupEntity;
import ru.hits.timeflowapi.entity.SubjectEntity;
import ru.hits.timeflowapi.entity.TeacherEntity;
import ru.hits.timeflowapi.exception.BadRequestException;
import ru.hits.timeflowapi.mapper.ClassroomMapper;
import ru.hits.timeflowapi.mapper.StudentGroupMapper;
import ru.hits.timeflowapi.mapper.SubjectMapper;
import ru.hits.timeflowapi.mapper.TeacherMapper;
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
        isExistSubject(subjectDto.getName());
        subjectRepository.save(subjectEntity);
        return new SubjectDto(subjectEntity);
    }

    public StudentGroupBasicDto addGroups(NewStudentGroupDto studentGroupBasicDto) {

        StudentGroupEntity studentGroupEntity = studentGroupMapper.newStudentGroupDtoToEntity(studentGroupBasicDto);
        isExistStudentGroup(studentGroupBasicDto.getNumber());
        studentGroupRepository.save(studentGroupEntity);
        return new StudentGroupBasicDto(studentGroupEntity);
    }

    public ClassroomDto addClassrooms(NewClassroomDto classroomDto) {

        ClassroomEntity classroomEntity = classroomMapper.newClassroomDtoToEntity(classroomDto);
        isExistClassroom(classroomDto.getNumber());
        classroomRepository.save(classroomEntity);
        return new ClassroomDto(classroomEntity);
    }

    public TeacherDto addTeachers(NewTeacherDto teacherDto) {

        TeacherEntity teacherEntity = teacherMapper.newTeacherDtoToEntity(teacherDto);
        isExistTeacher(teacherDto.getName(), teacherDto.getSurname(), teacherDto.getPatronymic());
        teacherRepository.save(teacherEntity);
        return new TeacherDto(teacherEntity);
    }

    private void isExistSubject(String name) {
        if (subjectRepository.existsAllByName(name)) {
            throw new BadRequestException("Такой предмет уже существует.");
        }
    }

    private void isExistTeacher(String name, String surname, String patronymic) {
        if (teacherRepository.existsAllByNameAndSurnameAndPatronymic(name, surname, patronymic)) {
            throw new BadRequestException("Такой преподаватель уже существует.");
        }
    }

    private void isExistClassroom(String name) {
        if (classroomRepository.existsAllByNumber(name)) {
            throw new BadRequestException("Такая аудитория уже существует.");
        }
    }

    private void isExistStudentGroup(int number) {
        if (studentGroupRepository.existsAllByNumber(number)) {
            throw new BadRequestException("Такая студенческая группа уже существует.");
        }
    }


}
