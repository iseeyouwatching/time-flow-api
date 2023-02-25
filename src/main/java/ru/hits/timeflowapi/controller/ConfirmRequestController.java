package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.requestconfirm.EmployeeRequestConfirmDto;
import ru.hits.timeflowapi.model.dto.requestconfirm.StudentRequestConfirmDto;
import ru.hits.timeflowapi.service.ConfirmRequestService;

import java.util.Optional;

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

}
