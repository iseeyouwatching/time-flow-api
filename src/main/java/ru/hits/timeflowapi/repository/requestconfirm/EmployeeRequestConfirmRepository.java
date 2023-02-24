package ru.hits.timeflowapi.repository.requestconfirm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;

import java.util.UUID;

@Repository
public interface EmployeeRequestConfirmRepository extends JpaRepository<EmployeeRequestConfirmEntity, UUID> {

    Page<EmployeeRequestConfirmEntity> findAllByIsClosed(Pageable pageable, boolean isClosed);

}
