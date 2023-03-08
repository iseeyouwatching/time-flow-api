package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.EmployeePostRepository;
import ru.hits.timeflowapi.util.validation.annotation.UniquePostNameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика для проверки ограничения {@link UniquePostNameValidation}.
 * С проверяемым объектом типа {@link String}.
 */
@Component
@RequiredArgsConstructor
public class UniquePostNameValidator implements ConstraintValidator<UniquePostNameValidation, String> {

    private final EmployeePostRepository employeePostRepository;

    /**
     * Метод для проверки существования должности сотрудника с таким названием.
     *
     * @param postName название должности, которое будет проверяться.
     * @param context  контекст, в котором вычисляется ограничение.
     * @return если в БД уже существует должность с названием {@code postName}, то {@code false}, иначе {@code true}.
     */
    @Override
    public boolean isValid(String postName, ConstraintValidatorContext context) {
        return !employeePostRepository.existsByPostName(postName);
    }
}
