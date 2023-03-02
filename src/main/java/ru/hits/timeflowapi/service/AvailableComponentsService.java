package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        List<LessonEntity> lessons;
        List<TimeslotEntity> found = new ArrayList<>();

        for (TimeslotEntity timeslot : timeslots) {
            lessons = lessonRepository.findByStudentGroupId(groupId, Sort.by("date"));
            for (LessonEntity lesson : lessons) {
                if (lesson.getDate().equals(date)) {
                    if (lesson.getTimeslot().getId().equals(timeslot.getId())) {
                        found.add(timeslot);
                    }
                }
            }
        }
        timeslots.removeAll(found);

        return timeslotMapper.timeslotListToDtoList(timeslots);
    }

    public List<ClassroomDto> getAvailableClassrooms(UUID timeslotId, LocalDate date) {

        List<ClassroomEntity> classrooms = classroomRepository.findAll();
        List<LessonEntity> lessons;

        List<ClassroomEntity> found = new ArrayList<>();


        for (ClassroomEntity classroom : classrooms) {
            lessons = lessonRepository.findByClassroom(classroom, Sort.by("date"));
            for (LessonEntity lesson : lessons) {
                if (lesson.getDate().equals(date) && lesson.getTimeslot().getId().equals(timeslotId)) {
                    found.add(classroom);
                }
            }
        }
        classrooms.removeAll(found);

        return classroomMapper.classroomListToDtoList(classrooms);
    }

    public List<TeacherDto> getAvailableTeachers(UUID timeslotId, LocalDate date) {

        List<TeacherEntity> teachers = teacherRepository.findAll();
        List<LessonEntity> lessons;
        List<TeacherEntity> found = new ArrayList<>();

        for (TeacherEntity teacher : teachers) {
            lessons = lessonRepository.findByTeacher(teacher, Sort.by("date"));
            for (LessonEntity lesson : lessons) {
                if (lesson.getDate().equals(date) && lesson.getTimeslot().getId().equals(timeslotId)) {
                    found.add(teacher);
                }
            }
        }
        teachers.removeAll(found);

        return teacherMapper.teacherListToDtoList(teachers);
    }
}
