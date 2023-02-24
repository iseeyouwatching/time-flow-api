package ru.hits.timeflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.*;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

    List<LessonEntity> findByStudentGroupAndWeek(StudentGroupEntity studentGroup, WeekEntity week);

    List<LessonEntity> findByTeacherAndWeek(TeacherEntity teacher, WeekEntity week);

    List<LessonEntity> findByClassroom(ClassroomEntity classroom);

}
