package com.layer.demo.validator;

import com.layer.demo.constants.Limits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return (validateName(name));
    }

    private boolean validateName(String name) {
        return !(name == null || name.length() >= Limits.MAX_NAME_LENGTH);
    }
}
