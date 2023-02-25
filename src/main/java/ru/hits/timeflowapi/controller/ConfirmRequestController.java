package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.service.ConfirmRequestService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class ConfirmRequestController {

    private final ConfirmRequestService confirmRequestService;

    @GetMapping("/student")
    @Operation(summary = "Получить заявки студентов.", tags = {"Заявки студентов."})
    public Page<StudentRequestConfirmDto> getStudentRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return confirmRequestService.getStudentRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("/student/{id}")
    @Operation(summary = "Получить информацию о заявке по ID.", tags = {"Заявки студентов."})
    public StudentRequestConfirmDto getStudentRequestById(@PathVariable UUID id) {
        return confirmRequestService.getStudentRequestById(id);
    }

    @PostMapping("/student/{id}/accept")
    @Operation(summary = "Одобрить заявку.", tags = {"Заявки студентов."})
    public StudentDto acceptStudentRequest(@PathVariable UUID id) {
        return confirmRequestService.confirmStudentRequest(id);
    }

    @PostMapping("/student/{id}/reject")
    @Operation(summary = "Отклонить заявку.", tags = {"Заявки студентов."})
    public StudentDto rejectStudentRequest(@PathVariable UUID id) {
        return confirmRequestService.rejectStudentRequest(id);
    }

    @GetMapping("/employee")
    @Operation(summary = "Получить заявки сотрудников.", tags = {"Заявки сотрудников."})
    public Page<EmployeeRequestConfirmDto> getEmployeeRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return confirmRequestService.getEmployeeRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("/employee/{id}")
    @Operation(summary = "Получить информацию о заявке по ID.", tags = {"Заявки сотрудников."})
    public EmployeeRequestConfirmDto getEmployeeRequestById(@PathVariable UUID id) {
        return confirmRequestService.getEmployeeRequestById(id);
    }

    @PostMapping("/employee/{id}/accept")
    @Operation(summary = "Одобрить заявку.", tags = {"Заявки сотрудников."})
    public EmployeeDto acceptEmployeeRequest(@PathVariable UUID id,
                                             @RequestParam List<UUID> postIds) {
        return confirmRequestService.confirmEmployeeRequest(id, postIds);
    }

    @PostMapping("/employee/{id}/reject")
    @Operation(summary = "Отклонить заявку.", tags = {"Заявки сотрудников."})
    public EmployeeDto rejectEmployeeRequest(@PathVariable UUID id) {
        return confirmRequestService.rejectEmployeeRequest(id);
    }


    @GetMapping("/employee/schedule-maker")
    @Operation(summary = "Получить заявки составителей расписаний.", tags = {"Заявки составителей расписаний."})
    public Page<EmployeeRequestConfirmDto> getScheduleMakerRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return confirmRequestService.getScheduleMakerRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("/employee/schedule-maker/{id}")
    @Operation(summary = "Получить информацию о заявке по ID.", tags = {"Заявки составителей расписаний."})
    public EmployeeRequestConfirmDto getScheduleMakerRequestById(@PathVariable UUID id) {
        return confirmRequestService.getScheduleMakerRequestById(id);
    }

    @PostMapping("/employee/schedule-maker/{id}/accept")
    @Operation(summary = "Одобрить заявку.", tags = {"Заявки составителей расписаний."})
    public EmployeeDto acceptScheduleMakerRequest(@PathVariable UUID id) {
        return confirmRequestService.confirmScheduleMakerRequest(id);
    }

    @PostMapping("/employee/schedule-maker/{id}/reject")
    @Operation(summary = "Отклонить заявку.", tags = {"Заявки составителей расписаний."})
    public EmployeeDto rejectScheduleMakerRequest(@PathVariable UUID id) {
        return confirmRequestService.rejectScheduleMakerRequest(id);
    }
}
