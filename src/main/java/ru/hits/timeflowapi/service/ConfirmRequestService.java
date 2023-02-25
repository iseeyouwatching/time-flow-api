package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.RequestMapper;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestConfirmRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmRequestService {

    private static final String SORT_PROPERTY = "creationDate";
    private final RequestMapper requestMapper;
    private final StudentRequestConfirmRepository studentRequestConfirmRepository;
    private final EmployeeRequestConfirmRepository employeeRequestConfirmRepository;
    private final ScheduleMakerRequestConfirmRepository scheduleMakerRequestConfirmRepository;

    public Page<StudentRequestConfirmDto> getStudentRequestsPage(int pageNumber,
                                                                 int pageSize,
                                                                 Sort.Direction direction,
                                                                 Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<StudentRequestConfirmEntity> students;

        if (isClosed.isPresent()) {
            students = studentRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            students = studentRequestConfirmRepository.findAll(pageable);
        }

        return students.map(requestMapper::studentRequestConfirmToDto);
    }

    public Page<EmployeeRequestConfirmDto> getEmployeeRequestsPage(int pageNumber,
                                                                   int pageSize,
                                                                   Sort.Direction direction,
                                                                   Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<ScheduleMakerRequestConfirmEntity> scheduleMakers;

        if (isClosed.isPresent()) {
            scheduleMakers = scheduleMakerRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            scheduleMakers = scheduleMakerRequestConfirmRepository.findAll(pageable);
        }

        return scheduleMakers.map(requestMapper::employeeRequestConfirmToDto);
    }

    public Page<EmployeeRequestConfirmDto> getScheduleMakerRequestsPage(int pageNumber,
                                                                        int pageSize,
                                                                        Sort.Direction direction,
                                                                        Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<EmployeeRequestConfirmEntity> employees;

        if (isClosed.isPresent()) {
            employees = employeeRequestConfirmRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            employees = employeeRequestConfirmRepository.findAll(pageable);
        }

        return employees.map(requestMapper::employeeRequestConfirmToDto);
    }

    public StudentRequestConfirmDto getStudentRequestById(UUID id) {
        Optional<StudentRequestConfirmEntity> request = studentRequestConfirmRepository.findById(id);

        if (request.isPresent()) {
            return requestMapper.studentRequestConfirmToDto(request.get());
        }

        throw new NotFoundException("Заявка студента не найдена. ID = " + id);
    }

    public EmployeeRequestConfirmDto getEmployeeRequestById(UUID id) {
        Optional<EmployeeRequestConfirmEntity> request = employeeRequestConfirmRepository.findById(id);

        if (request.isPresent()) {
            return requestMapper.employeeRequestConfirmToDto(request.get());
        }

        throw new NotFoundException("Заявка сотрудника не найдена. ID = " + id);
    }

    public EmployeeRequestConfirmDto getScheduleMakerRequestById(UUID id) {
        Optional<ScheduleMakerRequestConfirmEntity> request = scheduleMakerRequestConfirmRepository.findById(id);

        if (request.isPresent()) {
            return requestMapper.employeeRequestConfirmToDto(request.get());
        }

        throw new NotFoundException("Заявка составителя расписаний не найдена. ID = " + id);
    }

}
