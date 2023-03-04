package ru.hits.timeflowapi.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

    List<LessonEntity> findByStudentGroup(StudentGroupEntity studentGroup, Sort date);

    List<LessonEntity> findByTeacher(TeacherEntity teacher, Sort date);

    List<LessonEntity> findByClassroom(ClassroomEntity classroom, Sort date);

    LessonEntity findByTimeslotAndTeacherAndDate(Optional<TimeslotEntity> timeslot, Optional<TeacherEntity> teacher, LocalDate date);

    LessonEntity findByTimeslotIdAndTeacherAndDate(UUID timeslotId, TeacherEntity teacher, LocalDate date);

    LessonEntity findByTimeslotAndClassroomAndDate(Optional<TimeslotEntity> timeslot, Optional<ClassroomEntity> classroom, LocalDate date);

    LessonEntity findByTimeslotIdAndClassroomAndDate(UUID timeslotId, ClassroomEntity classroom, LocalDate date);

    LessonEntity findByTimeslotAndStudentGroupAndDate(Optional<TimeslotEntity> timeslot, Optional<StudentGroupEntity> studentGroup, LocalDate date);

    LessonEntity findByTimeslotAndStudentGroupIdAndDate(TimeslotEntity timeslot, UUID studentGroupId, LocalDate date);

}
