package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.studentgroup.StudentGroupBasicDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestConfirmRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmRequestService {

    private final StudentRequestConfirmRepository studentRequestConfirmRepository;
    private final EmployeeRequestConfirmRepository employeeRequestConfirmRepository;
    private final ScheduleMakerRequestConfirmRepository scheduleMakerRequestConfirmRepository;

    public Page<StudentRequestConfirmDto> getAllStudentRequestConfirmEntities(int pageNumber,
                                                                              int pageSize,
                                                                              Sort.Direction direction,
                                                                              Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, "creationDate");

        Page<StudentRequestConfirmEntity> students;

        if (isClosed.isPresent()) {
            students = studentRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            students = studentRequestConfirmRepository.findAll(pageable);
        }

        return students.map(requestEntity -> new StudentRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                new StudentDto(
                        requestEntity.getStudentDetails().getUser().getId(),
                        requestEntity.getStudentDetails().getUser().getEmail(),
                        requestEntity.getStudentDetails().getUser().getRole(),
                        requestEntity.getStudentDetails().getUser().getName(),
                        requestEntity.getStudentDetails().getUser().getSurname(),
                        requestEntity.getStudentDetails().getUser().getPatronymic(),
                        requestEntity.getStudentDetails().getUser().getAccountStatus(),
                        requestEntity.getStudentDetails().getUser().getSex(),
                        requestEntity.getStudentDetails().getUser().getStudent().getStudentNumber(),
                        new StudentGroupBasicDto(
                                requestEntity.getStudentDetails().getGroup().getId(),
                                requestEntity.getStudentDetails().getGroup().getNumber()
                        )
                )
        ));
    }

    public Page<EmployeeRequestConfirmDto> getAllEmployeeRequestConfirmEntities(int pageNumber,
                                                                                int pageSize,
                                                                                Sort.Direction direction,
                                                                                Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, "creationDate");

        Page<ScheduleMakerRequestConfirmEntity> scheduleMakers;

        if (isClosed.isPresent()) {
            scheduleMakers = scheduleMakerRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            scheduleMakers = scheduleMakerRequestConfirmRepository.findAll(pageable);
        }

        return scheduleMakers.map(requestEntity -> new EmployeeRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                new EmployeeDto(
                        requestEntity.getEmployeeDetails().getUser().getId(),
                        requestEntity.getEmployeeDetails().getUser().getEmail(),
                        requestEntity.getEmployeeDetails().getUser().getRole(),
                        requestEntity.getEmployeeDetails().getUser().getName(),
                        requestEntity.getEmployeeDetails().getUser().getSurname(),
                        requestEntity.getEmployeeDetails().getUser().getPatronymic(),
                        requestEntity.getEmployeeDetails().getUser().getAccountStatus(),
                        requestEntity.getEmployeeDetails().getUser().getSex(),
                        requestEntity.getEmployeeDetails().getContactNumber()
                )
        ));
    }

    public Page<EmployeeRequestConfirmDto> getAllScheduleMakerRequestConfirmEntities(int pageNumber,
                                                                                     int pageSize,
                                                                                     Sort.Direction direction,
                                                                                     Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, "creationDate");

        Page<EmployeeRequestConfirmEntity> employees;

        if (isClosed.isPresent()) {
            employees = employeeRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            employees = employeeRequestConfirmRepository.findAll(pageable);
        }

        return employees.map(requestEntity -> new EmployeeRequestConfirmDto(
                requestEntity.getId(),
                requestEntity.getCreationDate(),
                requestEntity.getClosedDate(),
                requestEntity.isClosed(),
                new EmployeeDto(
                        requestEntity.getEmployeeDetails().getUser().getId(),
                        requestEntity.getEmployeeDetails().getUser().getEmail(),
                        requestEntity.getEmployeeDetails().getUser().getRole(),
                        requestEntity.getEmployeeDetails().getUser().getName(),
                        requestEntity.getEmployeeDetails().getUser().getSurname(),
                        requestEntity.getEmployeeDetails().getUser().getPatronymic(),
                        requestEntity.getEmployeeDetails().getUser().getAccountStatus(),
                        requestEntity.getEmployeeDetails().getUser().getSex(),
                        requestEntity.getEmployeeDetails().getContactNumber()
                )
        ));
    }

}
