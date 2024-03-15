package com.dedagroup.springboottraining.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        //At leats 1 digit, 1 uppercase letter, 1 lowercase letter, 8 characters
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        return password.matches(PASSWORD_PATTERN);
    }
}
