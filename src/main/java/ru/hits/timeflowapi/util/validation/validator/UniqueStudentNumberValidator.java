package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.StudentDetailsRepository;
import ru.hits.timeflowapi.util.validation.annotation.UniqueStudentNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Логика для проверки ограничения {@link UniqueStudentNumberValidation}.
 * С проверяемым объектом типа {@link String}.
 */
@Component
@RequiredArgsConstructor
public class UniqueStudentNumberValidator implements ConstraintValidator<UniqueStudentNumberValidation, String> {

    private final StudentDetailsRepository studentDetailsRepository;

    /**
     * Метод для проверки на уникальность номер студенческого билета.
     *
     * @param studentNumber номер студенческого билета.
     * @param context       контекст, в котором вычисляется ограничение.
     * @return если в БД уже есть пользователь с номером студенческого билета {@code studentNumber},
     * то {@code false}, иначе {@code true}.
     */
    @Override
    public boolean isValid(String studentNumber, ConstraintValidatorContext context) {
        return !studentDetailsRepository.existsByStudentNumber(studentNumber);
    }

}

