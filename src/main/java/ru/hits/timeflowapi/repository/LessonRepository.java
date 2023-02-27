package ru.hits.timeflowapi.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.*;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

    List<LessonEntity> findByStudentGroup(StudentGroupEntity studentGroup, Sort date);

    List<LessonEntity> findByTeacher(TeacherEntity teacher, Sort date);

    List<LessonEntity> findByClassroom(ClassroomEntity classroom, Sort date);

}
