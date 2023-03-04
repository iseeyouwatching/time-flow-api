package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.request.EmployeeRequestDto;
import ru.hits.timeflowapi.model.dto.request.StudentRequestDto;
import ru.hits.timeflowapi.model.dto.user.EmployeeDto;
import ru.hits.timeflowapi.model.dto.user.StudentDto;
import ru.hits.timeflowapi.service.request.ManageRequestService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ConfirmRequestController {

    private final ManageRequestService manageRequestService;

    @GetMapping("/student-requests")
    @Operation(
            summary = "Получить заявки студентов.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки студентов"}
    )
    public Page<StudentRequestDto> getStudentRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return manageRequestService.getStudentRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("/student-requests/{id}")
    @Operation(
            summary = "Получить информацию о заявке по ID.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки студентов"}
    )
    public StudentRequestDto getStudentRequestById(@PathVariable UUID id) {
        return manageRequestService.getStudentRequestById(id);
    }

    @PostMapping("/student-requests/{id}/accept")
    @Operation(
            summary = "Одобрить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки студентов"}
    )
    public StudentDto acceptStudentRequest(@PathVariable UUID id) {
        return manageRequestService.confirmStudentRequest(id);
    }

    @PostMapping("/student-requests/{id}/reject")
    @Operation(
            summary = "Отклонить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки студентов"}
    )
    public StudentDto rejectStudentRequest(@PathVariable UUID id) {
        return manageRequestService.rejectStudentRequest(id);
    }

    @GetMapping("/employee-requests")
    @Operation(
            summary = "Получить заявки сотрудников.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки сотрудников"}
    )
    public Page<EmployeeRequestDto> getEmployeeRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return manageRequestService.getEmployeeRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("employee-requests/{id}")
    @Operation(
            summary = "Получить информацию о заявке по ID.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки сотрудников"}
    )
    public EmployeeRequestDto getEmployeeRequestById(@PathVariable UUID id) {
        return manageRequestService.getEmployeeRequestById(id);
    }

    @PostMapping("/employee-requests/{id}/accept")
    @Operation(
            summary = "Одобрить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки сотрудников"}
    )
    public EmployeeDto acceptEmployeeRequest(@PathVariable UUID id,
                                             @RequestParam List<UUID> postIds) {
        return manageRequestService.confirmEmployeeRequest(id, postIds);
    }

    @PostMapping("/employee-requests/{id}/reject")
    @Operation(
            summary = "Отклонить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки сотрудников"}
    )
    public EmployeeDto rejectEmployeeRequest(@PathVariable UUID id) {
        return manageRequestService.rejectEmployeeRequest(id);
    }


    @GetMapping("schedule-maker-requests")
    @Operation(
            summary = "Получить заявки составителей расписаний.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки составителей расписаний"}
    )
    public Page<EmployeeRequestDto> getScheduleMakerRequests(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) Optional<Boolean> isClosed
    ) {
        return manageRequestService.getScheduleMakerRequestsPage(
                pageNumber,
                pageSize,
                sortDirection,
                isClosed
        );
    }

    @GetMapping("schedule-maker-requests/{id}")
    @Operation(
            summary = "Получить информацию о заявке по ID.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки составителей расписаний"}
    )
    public EmployeeRequestDto getScheduleMakerRequestById(@PathVariable UUID id) {
        return manageRequestService.getScheduleMakerRequestById(id);
    }

    @PostMapping("schedule-maker-requests/{id}/accept")
    @Operation(
            summary = "Одобрить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки составителей расписаний"}
    )
    public EmployeeDto acceptScheduleMakerRequest(@PathVariable UUID id) {
        return manageRequestService.confirmScheduleMakerRequest(id);
    }

    @PostMapping("schedule-maker-requests/{id}/reject")
    @Operation(
            summary = "Отклонить заявку.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Заявки составителей расписаний"}
    )
    public EmployeeDto rejectScheduleMakerRequest(@PathVariable UUID id) {
        return manageRequestService.rejectScheduleMakerRequest(id);
    }
}
