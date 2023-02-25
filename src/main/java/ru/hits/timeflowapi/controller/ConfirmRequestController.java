package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.service.ConfirmRequestService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class ConfirmRequestController {

    private final ConfirmRequestService confirmRequestService;

    @GetMapping("/student")
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
    public StudentRequestConfirmDto getStudentRequestById(@PathVariable UUID id) {
        return confirmRequestService.getStudentRequestById(id);
    }

    @GetMapping("/employee")
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
    public EmployeeRequestConfirmDto getEmployeeRequestById(@PathVariable UUID id) {
        return confirmRequestService.getEmployeeRequestById(id);
    }


    @GetMapping("/schedule-maker")
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

    @GetMapping("/schedule-maker/{id}")
    public EmployeeRequestConfirmDto getScheduleMakerRequestById(@PathVariable UUID id) {
        return confirmRequestService.getScheduleMakerRequestById(id);
    }

}
