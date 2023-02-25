package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.RequestMapper;
import ru.hits.timeflowapi.mapper.UserMapper;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestConfirmEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestConfirmEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestConfirmRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestConfirmRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmRequestService {

    private static final String SORT_PROPERTY = "creationDate";
    private final UserMapper userMapper;
    private final RequestMapper requestMapper;
    private final StudentRequestConfirmRepository studentRequestConfirmRepository;
    private final EmployeeRequestConfirmRepository employeeRequestConfirmRepository;
    private final ScheduleMakerRequestConfirmRepository scheduleMakerRequestConfirmRepository;
    private final EmployeePostService employeePostService;

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

    public Page<EmployeeRequestConfirmDto> getScheduleMakerRequestsPage(int pageNumber,
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

    public Page<EmployeeRequestConfirmDto> getEmployeeRequestsPage(int pageNumber,
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

    public StudentRequestConfirmDto getStudentRequestById(UUID requestId) {
        StudentRequestConfirmEntity request = getStudentRequest(requestId);

        return requestMapper.studentRequestConfirmToDto(request);
    }

    public EmployeeRequestConfirmDto getEmployeeRequestById(UUID requestId) {
        EmployeeRequestConfirmEntity request = getEmployeeRequest(requestId);

        return requestMapper.employeeRequestConfirmToDto(request);
    }

    public EmployeeRequestConfirmDto getScheduleMakerRequestById(UUID id) {
        ScheduleMakerRequestConfirmEntity request = getScheduleMakerRequest(id);

        return requestMapper.employeeRequestConfirmToDto(request);
    }

    public StudentDto confirmStudentRequest(UUID requestId) {
        StudentRequestConfirmEntity request = getStudentRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getStudentDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = studentRequestConfirmRepository.save(request);

        return userMapper.studentDetailsToStudentDto(request.getStudentDetails());
    }

    public EmployeeDto confirmEmployeeRequest(UUID requestId, List<UUID> postIds) {
        EmployeeRequestConfirmEntity request = getEmployeeRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        List<EmployeePostEntity> employeePostEntities = postIds
                .stream()
                .map(employeePostService::getPostEntityById)
                .toList();

        request.getEmployeeDetails().setPosts(employeePostEntities);

        request = employeeRequestConfirmRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public EmployeeDto confirmScheduleMakerRequest(UUID requestId) {
        ScheduleMakerRequestConfirmEntity request = getScheduleMakerRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request
                .getEmployeeDetails()
                .setPosts(List.of(employeePostService.getPostEntityByPostRole("ROLE_SCHEDULE_MAKER")));

        request = scheduleMakerRequestConfirmRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public StudentDto rejectStudentRequest(UUID requestId) {
        StudentRequestConfirmEntity request = getStudentRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getStudentDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = studentRequestConfirmRepository.save(request);

        return userMapper.studentDetailsToStudentDto(request.getStudentDetails());
    }

    public EmployeeDto rejectEmployeeRequest(UUID requestId) {
        EmployeeRequestConfirmEntity request = getEmployeeRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = employeeRequestConfirmRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public EmployeeDto rejectScheduleMakerRequest(UUID requestId) {
        ScheduleMakerRequestConfirmEntity request = getScheduleMakerRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = scheduleMakerRequestConfirmRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    void checkRequestStatus(boolean isClosed) {
        if (isClosed) {
            throw new ConflictException("Заявка уже закрыта.");
        }
    }

    private StudentRequestConfirmEntity getStudentRequest(UUID requestId) {
        return studentRequestConfirmRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка студента не найдена, id = '" + requestId + "'.");
                });
    }

    private ScheduleMakerRequestConfirmEntity getScheduleMakerRequest(UUID requestId) {
        return scheduleMakerRequestConfirmRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка составителя расписаний не найдена, id = '" + requestId + "'.");
                });
    }

    private EmployeeRequestConfirmEntity getEmployeeRequest(UUID requestId) {
        return employeeRequestConfirmRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка сотрудникам не найдена, id = '" + requestId + "'.");
                });
    }

}
