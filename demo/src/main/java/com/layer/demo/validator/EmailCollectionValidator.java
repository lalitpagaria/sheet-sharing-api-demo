package com.layer.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class EmailCollectionValidator implements ConstraintValidator<ValidEmailCollection, Collection<String>> {

    @Override
    public void initialize(ValidEmailCollection constraintAnnotation) {

    }

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        EmailValidator validator = new EmailValidator();
        for (String s : value) {
            if (!validator.isValid(s, context)) {
                return false;
            }
        }
        return true;
    }
}
