package com.dedagroup.springboottraining.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class CredentialChecker implements ConstraintValidator<CheckCredentials, Object> {

    private String field;
    private String fieldMatch;


    @Override
    public void initialize(CheckCredentials constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }


    @Override
    public boolean isValid(Object input, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(input).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(input).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            System.out.println("fieldValue.equals(fieldMatchValue) " + fieldValue.equals(fieldMatchValue));
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }

    }
}
