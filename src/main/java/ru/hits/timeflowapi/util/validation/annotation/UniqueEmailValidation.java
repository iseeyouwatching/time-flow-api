package ru.hits.timeflowapi.util.validation.annotation;


import ru.hits.timeflowapi.util.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Аннотация для добавления проверки почты на уникальность в БД.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmailValidation {

    String message() default "Почта занята.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
