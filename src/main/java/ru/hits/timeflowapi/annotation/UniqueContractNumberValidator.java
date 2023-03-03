package ru.hits.timeflowapi.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика проверки трудового договора на уникальность для аннотации {@link UniqueContractNumberValidation}.
 */
@Component
@RequiredArgsConstructor
public class UniqueContractNumberValidator implements ConstraintValidator<UniqueContractNumberValidation, String> {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public boolean isValid(String contractNumber, ConstraintValidatorContext context) {
        return !employeeDetailsRepository.existsByContractNumber(contractNumber);
    }

}
