package ru.hits.timeflowapi.util.validation.annotation;

import ru.hits.timeflowapi.util.validation.validator.ExistStudentGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Аннотация для проверки существования студенческой группы по её {@code id}.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistStudentGroupValidator.class)
public @interface ExistStudentGroupValidation {

    String message() default "Студенческой группы с таким ID не существует.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
