package ru.hits.timeflowapi.repository.requestconfirm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;

import java.util.UUID;

@Repository
public interface StudentRequestConfirmRepository extends JpaRepository<StudentRequestConfirmEntity, UUID> {
}
