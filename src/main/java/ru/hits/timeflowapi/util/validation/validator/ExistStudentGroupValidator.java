package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.util.validation.annotation.ExistStudentGroupValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

/**
 * Логика проверки существования студенческой группы по её {@code id} для {@link ExistStudentGroupValidation}.
 */
@Component
@RequiredArgsConstructor
public class ExistStudentGroupValidator implements ConstraintValidator<ExistStudentGroupValidation, UUID> {

    private final StudentGroupRepository studentGroupRepository;

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext context) {
        return studentGroupRepository.existsById(id);
    }
}
