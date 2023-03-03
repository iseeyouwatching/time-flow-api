package ru.hits.timeflowapi.util.validation.annotation;

import ru.hits.timeflowapi.util.validation.validator.UniqueStudentNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Аннотация для проверки на уникальность номера студенческого билета.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueStudentNumberValidator.class)
public @interface UniqueStudentNumberValidation {

    String message() default "Номер студенческого билета занят.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
