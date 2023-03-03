package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.UserRepository;
import ru.hits.timeflowapi.util.validation.annotation.UniqueEmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика проверки почты на уникальность для аннотации {@link UniqueEmailValidation}.
 */
@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValidation, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(email);
    }

}
