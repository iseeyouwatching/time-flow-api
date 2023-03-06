package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;
import ru.hits.timeflowapi.repository.EmployeePostRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeePostService {

    private final EmployeePostRepository employeePostRepository;

    public EmployeePostEntity getPostEntityById(UUID id) {
        return employeePostRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Должность с заданным ID не найдена");
                });
    }

    public EmployeePostEntity getPostEntityByPostRole(String postRole) {
        return employeePostRepository
                .findByPostRole(postRole)
                .orElseThrow(() -> {
                    throw new NotFoundException("Должность не найдена");
                });
    }

}
