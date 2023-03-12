package ru.hits.timeflowapi.util.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.util.validation.annotation.ExistStudentGroupValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

/**
 * Логика для проверки ограничения {@link ExistStudentGroupValidation}.
 * С проверяемым объектом типа {@link UUID}.
 */
@Component
@RequiredArgsConstructor
public class ExistStudentGroupValidator implements ConstraintValidator<ExistStudentGroupValidation, UUID> {

    private final StudentGroupRepository studentGroupRepository;

    /**
     * Метод для проверки существования студенческой группы с заданным {@code id}.
     *
     * @param id      {@code id} студенческой группы.
     * @param context контекст, в котором вычисляется ограничение.
     * @return если студенческая группа с заданным {@code id} существует, то {@code true}, иначе {@code false}.
     */
    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext context) {
        return studentGroupRepository.existsById(id);
    }

}
