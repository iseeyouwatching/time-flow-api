package ru.hits.timeflowapi.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.employeepost.EmployeePostDto;
import ru.hits.timeflowapi.service.EmployeePostService;

import java.util.List;

/**
 * Контроллер для работы с должностями сотрудников.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Должности сотрудников")
public class EmployeePostController {

    private final EmployeePostService employeePostService;

    /**
     * Метод для получения списка должностей сотрудников.
     *
     * @return список должностей сотрудников.
     */
    @Operation(
            summary = "Получить список должностей сотрудников.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/employee-posts")
    public List<EmployeePostDto> getEmployeePosts() {
        return employeePostService.getEmployeePosts();
    }

}

