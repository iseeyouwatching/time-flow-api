package ru.hits.timeflowapi.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика по проверки на уникальность номера студенческого билета для аннотации {@link UniqueStudentNumberValidation}
 */
@Component
@RequiredArgsConstructor
public class UniqueStudentNumberValidator implements ConstraintValidator<UniqueStudentNumberValidation, String> {

    private final StudentDetailsRepository studentDetailsRepository;

    @Override
    public boolean isValid(String studentNumber, ConstraintValidatorContext context) {
        return !studentDetailsRepository.existsByStudentNumber(studentNumber);
    }
}

