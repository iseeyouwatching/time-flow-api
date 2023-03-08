package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.mapper.PostMapper;
import ru.hits.timeflowapi.dto.employeepost.EmployeePostDto;
import ru.hits.timeflowapi.dto.employeepost.NewEmployeePostDto;
import ru.hits.timeflowapi.entity.EmployeePostEntity;
import ru.hits.timeflowapi.repository.EmployeePostRepository;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с должностями сотрудников.
 */
@Service
@RequiredArgsConstructor
public class EmployeePostService {

    private final EmployeePostRepository employeePostRepository;
    private final PostMapper postMapper;

    /**
     * Метод для получения сущности должности сотрудника по её {@code id}.
     *
     * @param id {@code id} должности сотрудника.
     * @return сущность должности сотрудника.
     * @throws NotFoundException возникает если должности с заданным {@code id} не существует.
     */
    public EmployeePostEntity getPostEntityById(UUID id) {
        return employeePostRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Должность с заданным ID не найдена");
                });
    }

    /**
     * Метод для получения сущности должности сотрудника по её {@code postRole}.
     *
     * @param postRole должность сотрудника.
     * @return сущность должности сотрудника.
     * @throws NotFoundException возникает если должности с заданным {@code postRole} не существует.
     */
    public EmployeePostEntity getPostEntityByPostRole(String postRole) {
        return employeePostRepository
                .findByPostRole(postRole)
                .orElseThrow(() -> {
                    throw new NotFoundException("Должность не найдена");
                });
    }

    /**
     * Метод для получения списка {@link EmployeePostDto}.
     *
     * @return список должностей сотрудников, которые представлены {@link EmployeePostDto}.
     */
    public List<EmployeePostDto> getEmployeePosts() {
        return employeePostRepository
                .findAll(Sort.by("postName")).stream()
                .map(postMapper::employeePostToDto)
                .toList();
    }

    /**
     * Метод для добавления должности в БД.
     *
     * @param newEmployeePostDto dto с данными новой должности.
     * @return dto, сохраненной должности.
     */
    public EmployeePostDto addEmployeePost(NewEmployeePostDto newEmployeePostDto) {
        EmployeePostEntity employeePost = postMapper.newEmployeePostDtoToEntity(newEmployeePostDto);
        return postMapper.employeePostToDto(employeePostRepository.save(employeePost));
    }

}
