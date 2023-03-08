package ru.hits.timeflowapi.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.dto.employeepost.EmployeePostDto;
import ru.hits.timeflowapi.dto.employeepost.NewEmployeePostDto;
import ru.hits.timeflowapi.service.EmployeePostService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с должностями сотрудников.
 */
@RestController
@RequestMapping("/api/v1/employee-posts")
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
    @GetMapping
    public List<EmployeePostDto> getEmployeePosts() {
        return employeePostService.getEmployeePosts();
    }

    @Operation(
            summary = "Добавить новую должность.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping
    public EmployeePostDto addEmployeePost(@Valid @RequestBody NewEmployeePostDto newEmployeePostDto) {
        return employeePostService.addEmployeePost(newEmployeePostDto);
    }

}

