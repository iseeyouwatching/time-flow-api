package ru.hits.timeflowapi.service.request;

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
import ru.hits.timeflowapi.model.dto.request.EmployeeRequestDto;
import ru.hits.timeflowapi.model.dto.request.StudentRequestDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.EmployeeRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.ScheduleMakerRequestEntity;
import ru.hits.timeflowapi.model.entity.requestconfirm.StudentRequestEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.repository.requestconfirm.EmployeeRequestRepository;
import ru.hits.timeflowapi.repository.requestconfirm.ScheduleMakerRequestRepository;
import ru.hits.timeflowapi.repository.requestconfirm.StudentRequestRepository;
import ru.hits.timeflowapi.service.EmployeePostService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageRequestService {

    private static final String SORT_PROPERTY = "creationDate";
    private final UserMapper userMapper;
    private final RequestMapper requestMapper;
    private final StudentRequestRepository studentRequestRepository;
    private final EmployeeRequestRepository employeeRequestRepository;
    private final ScheduleMakerRequestRepository scheduleMakerRequestRepository;
    private final EmployeePostService employeePostService;

    public Page<StudentRequestDto> getStudentRequestsPage(int pageNumber,
                                                          int pageSize,
                                                          Sort.Direction direction,
                                                          Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<StudentRequestEntity> students;

        if (isClosed.isPresent()) {
            students = studentRequestRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            students = studentRequestRepository.findAll(pageable);
        }

        return students.map(requestMapper::studentRequestToDto);
    }

    public Page<EmployeeRequestDto> getScheduleMakerRequestsPage(int pageNumber,
                                                                 int pageSize,
                                                                 Sort.Direction direction,
                                                                 Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<ScheduleMakerRequestEntity> scheduleMakers;

        if (isClosed.isPresent()) {
            scheduleMakers = scheduleMakerRequestRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            scheduleMakers = scheduleMakerRequestRepository.findAll(pageable);
        }

        return scheduleMakers.map(requestMapper::employeeRequestToDto);
    }

    public Page<EmployeeRequestDto> getEmployeeRequestsPage(int pageNumber,
                                                            int pageSize,
                                                            Sort.Direction direction,
                                                            Optional<Boolean> isClosed
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, SORT_PROPERTY);

        Page<EmployeeRequestEntity> employees;

        if (isClosed.isPresent()) {
            employees = employeeRequestRepository
                    .findAllByIsClosed(
                            pageable,
                            isClosed.get());
        } else {
            employees = employeeRequestRepository.findAll(pageable);
        }

        return employees.map(requestMapper::employeeRequestToDto);
    }

    public StudentRequestDto getStudentRequestById(UUID requestId) {
        StudentRequestEntity request = getStudentRequest(requestId);

        return requestMapper.studentRequestToDto(request);
    }

    public EmployeeRequestDto getEmployeeRequestById(UUID requestId) {
        EmployeeRequestEntity request = getEmployeeRequest(requestId);

        return requestMapper.employeeRequestToDto(request);
    }

    public EmployeeRequestDto getScheduleMakerRequestById(UUID id) {
        ScheduleMakerRequestEntity request = getScheduleMakerRequest(id);

        return requestMapper.employeeRequestToDto(request);
    }

    public StudentDto confirmStudentRequest(UUID requestId) {
        StudentRequestEntity request = getStudentRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getStudentDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = studentRequestRepository.save(request);

        return userMapper.studentDetailsToStudentDto(request.getStudentDetails());
    }

    public EmployeeDto confirmEmployeeRequest(UUID requestId, List<UUID> postIds) {
        EmployeeRequestEntity request = getEmployeeRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        List<EmployeePostEntity> employeePostEntities = postIds
                .stream()
                .map(employeePostService::getPostEntityById)
                .toList();

        request.getEmployeeDetails().setPosts(employeePostEntities);

        request = employeeRequestRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public EmployeeDto confirmScheduleMakerRequest(UUID requestId) {
        ScheduleMakerRequestEntity request = getScheduleMakerRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.ACTIVATE);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request
                .getEmployeeDetails()
                .setPosts(List.of(employeePostService.getPostEntityByPostRole("ROLE_SCHEDULE_MAKER")));

        request = scheduleMakerRequestRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public StudentDto rejectStudentRequest(UUID requestId) {
        StudentRequestEntity request = getStudentRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getStudentDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = studentRequestRepository.save(request);

        return userMapper.studentDetailsToStudentDto(request.getStudentDetails());
    }

    public EmployeeDto rejectEmployeeRequest(UUID requestId) {
        EmployeeRequestEntity request = getEmployeeRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = employeeRequestRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    public EmployeeDto rejectScheduleMakerRequest(UUID requestId) {
        ScheduleMakerRequestEntity request = getScheduleMakerRequest(requestId);

        checkRequestStatus(request.isClosed());

        request.getEmployeeDetails().getUser().setAccountStatus(AccountStatus.DENIED);
        request.setClosed(true);
        request.setClosedDate(new Date());

        request = scheduleMakerRequestRepository.save(request);

        return userMapper.employeeDetailsToEmployeeDto(request.getEmployeeDetails());
    }

    void checkRequestStatus(boolean isClosed) {
        if (isClosed) {
            throw new ConflictException("Заявка уже закрыта.");
        }
    }

    private StudentRequestEntity getStudentRequest(UUID requestId) {
        return studentRequestRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка студента не найдена, id = '" + requestId + "'.");
                });
    }

    private ScheduleMakerRequestEntity getScheduleMakerRequest(UUID requestId) {
        return scheduleMakerRequestRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка составителя расписаний не найдена, id = '" + requestId + "'.");
                });
    }

    private EmployeeRequestEntity getEmployeeRequest(UUID requestId) {
        return employeeRequestRepository
                .findById(requestId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Заявка сотрудникам не найдена, id = '" + requestId + "'.");
                });
    }

}
