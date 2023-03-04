package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.mapper.ClassroomMapper;
import ru.hits.timeflowapi.mapper.TeacherMapper;
import ru.hits.timeflowapi.mapper.TimeslotMapper;
import ru.hits.timeflowapi.model.dto.TimeslotDto;
import ru.hits.timeflowapi.model.dto.classroom.ClassroomDto;
import ru.hits.timeflowapi.model.dto.teacher.TeacherDto;
import ru.hits.timeflowapi.model.entity.ClassroomEntity;
import ru.hits.timeflowapi.model.entity.LessonEntity;
import ru.hits.timeflowapi.model.entity.TeacherEntity;
import ru.hits.timeflowapi.model.entity.TimeslotEntity;
import ru.hits.timeflowapi.repository.ClassroomRepository;
import ru.hits.timeflowapi.repository.LessonRepository;
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

    private final LessonRepository lessonRepository;
    private final TeacherMapper teacherMapper;
    private final ClassroomMapper classroomMapper;
    private final TimeslotMapper timeslotMapper;


    public List<TimeslotDto> getAvailableTimeslots(UUID groupId, LocalDate date) {

        List<TimeslotEntity> timeslots = timeslotRepository.findAll();
        LessonEntity lesson;
        List<TimeslotEntity> found = new ArrayList<>();

        for (TimeslotEntity timeslot : timeslots) {
            lesson = lessonRepository.findByTimeslotAndStudentGroupIdAndDate(timeslot, groupId, date);
            if (lesson != null) {
                found.add(timeslot);
            }
        }
        timeslots.removeAll(found);

        return timeslotMapper.timeslotListToDtoList(timeslots);
    }

    public List<ClassroomDto> getAvailableClassrooms(UUID timeslotId, LocalDate date) {

        List<ClassroomEntity> classrooms = classroomRepository.findAll();
        LessonEntity lesson;
        List<ClassroomEntity> found = new ArrayList<>();

        for (ClassroomEntity classroom : classrooms) {
            lesson = lessonRepository.findByTimeslotIdAndClassroomAndDate(timeslotId, classroom, date);
            if (lesson != null) {
                found.add(classroom);
            }
        }
        classrooms.removeAll(found);

        return classroomMapper.classroomListToDtoList(classrooms);
    }

    public List<TeacherDto> getAvailableTeachers(UUID timeslotId, LocalDate date) {

        List<TeacherEntity> teachers = teacherRepository.findAll();
        LessonEntity lesson;
        List<TeacherEntity> found = new ArrayList<>();

        for (TeacherEntity teacher : teachers) {
            lesson = lessonRepository.findByTimeslotIdAndTeacherAndDate(timeslotId, teacher, date);
            if (lesson != null) {
                found.add(teacher);
            }
        }
        teachers.removeAll(found);

        return teacherMapper.teacherListToDtoList(teachers);
    }
}
