package ru.hits.timeflowapi.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Аннотация для добавления проверки трудового договора на уникальность в БД.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueContractNumberValidator.class)
public @interface UniqueContractNumberValidation {

    String message() default "Сотрудник с таким номером трудового договора уже существует.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
