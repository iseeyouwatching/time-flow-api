package ru.hits.timeflowapi.util.validation.annotation;

import ru.hits.timeflowapi.util.validation.validator.UniquePostNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Валидирующая аннотация. Она нужна для проверки на уникальность
 * названия должности.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniquePostNameValidator.class)
public @interface UniquePostNameValidation {

    String message() default "Должность с таким названием уже существует.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
