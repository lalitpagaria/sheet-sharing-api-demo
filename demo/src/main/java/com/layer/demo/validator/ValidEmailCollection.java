package com.layer.demo.validator;

import com.layer.demo.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailCollectionValidator.class)
@Documented
public @interface ValidEmailCollection {
    String message() default "Invalid Email List";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
