package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.util.validation.annotation.UniqueContractNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика для проверки ограничения {@link UniqueContractNumberValidation}.
 * С проверяемым объектом типа {@link String}.
 */
@Component
@RequiredArgsConstructor
public class UniqueContractNumberValidator implements ConstraintValidator<UniqueContractNumberValidation, String> {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    /**
     * Метод для проверки на уникальность номера трудового договора сотрудника.
     *
     * @param contractNumber номер трудового договора сотрудника.
     * @param context        контекст, в котором вычисляется ограничение.
     * @return если в БД уже есть сотрудник с номером трудового договора {@code contractNumber}, то {@code false},
     * иначе {@code true}.
     */
    @Override
    public boolean isValid(String contractNumber, ConstraintValidatorContext context) {
        return !employeeDetailsRepository.existsByContractNumber(contractNumber);
    }

}
