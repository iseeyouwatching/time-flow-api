package ru.hits.timeflowapi.repository.requestconfirm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestEntity;

import java.util.UUID;

@Repository
public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity, UUID> {

    Page<EmployeeRequestEntity> findAllByIsClosed(Pageable pageable, boolean isClosed);

}
