package ru.hits.timeflowapi.util.validation.annotation;

import ru.hits.timeflowapi.util.validation.validator.UniqueContractNumberValidator;

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
 * номера трудового договора сотрудника.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueContractNumberValidator.class)
public @interface UniqueContractNumberValidation {

    String message() default "Номер трудового договора занят.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
