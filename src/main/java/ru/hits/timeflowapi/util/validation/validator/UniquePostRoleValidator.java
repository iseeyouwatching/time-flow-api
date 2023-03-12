package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.EmployeePostRepository;
import ru.hits.timeflowapi.util.validation.annotation.UniquePostRoleValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика для проверки ограничения {@link UniquePostRoleValidation}.
 * С проверяемым объектом типа {@link String}.
 */
@Component
@RequiredArgsConstructor
public class UniquePostRoleValidator implements ConstraintValidator<UniquePostRoleValidation, String> {

    private final EmployeePostRepository employeePostRepository;

    /**
     * Метод для проверки существования должности сотрудника с таким названием роли.
     *
     * @param postRole название роли, относящейся к должности, которая будет проверяться.
     * @param context  контекст, в котором вычисляется ограничение.
     * @return если в БД уже существует роль должности с названием {@code postRole},
     * то {@code false}, иначе {@code true}.
     */
    @Override
    public boolean isValid(String postRole, ConstraintValidatorContext context) {
        return !employeePostRepository.existsByPostRole(postRole);
    }

}
