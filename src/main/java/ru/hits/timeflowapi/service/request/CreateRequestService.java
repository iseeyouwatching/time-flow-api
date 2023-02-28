package ru.hits.timeflowapi.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.StudentDetailsEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestEntity;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreateRequestService {

    private final ScheduleMakerRequestRepository scheduleMakerRequestRepository;
    private final EmployeeRequestRepository employeeRequestRepository;
    private final StudentRequestRepository studentRequestRepository;

    /**
     * Метод для создания и сохранения в БД заявки на подтверждение
     * аккаунта сотруднику с должностью "Составитель расписаний".
     *
     * @param employeeDetails dto с информацией о сотруднике.
     */
    public void createAndSaveScheduleMakerRequest(EmployeeDetailsEntity employeeDetails) {
        ScheduleMakerRequestEntity scheduleMakerRequest = ScheduleMakerRequestEntity
                .builder()
                .employeeDetails(employeeDetails)
                .creationDate(new Date())
                .isClosed(false)
                .build();

        scheduleMakerRequestRepository.save(scheduleMakerRequest);
    }

    /**
     * Метод для создания и сохранения в БД заявки на подтверждение
     * аккаунта сотруднику.
     *
     * @param employeeDetails dto с информацией о сотруднике.
     */
    public void createAndSaveEmployeeRequest(EmployeeDetailsEntity employeeDetails) {
        EmployeeRequestEntity employeeRequestConfirm = EmployeeRequestEntity
                .builder()
                .employeeDetails(employeeDetails)
                .creationDate(new Date())
                .isClosed(false)
                .build();

        employeeRequestRepository.save(employeeRequestConfirm);
    }

    /**
     * Метод для создания и сохранения в БД заявки на подтверждение
     * аккаунта студенту.
     *
     * @param studentDetails dto с информацией о студенте.
     */
    public void createAndSaveStudentRequest(StudentDetailsEntity studentDetails) {
        StudentRequestEntity studentRequest = StudentRequestEntity
                .builder()
                .studentDetails(studentDetails)
                .creationDate(new Date())
                .isClosed(false)
                .build();

        studentRequestRepository.save(studentRequest);
    }

}
