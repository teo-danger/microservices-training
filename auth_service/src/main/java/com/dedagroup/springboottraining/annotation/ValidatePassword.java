package com.dedagroup.springboottraining.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidatePassword {

    public String message() default "Invalid Password: it should contain at least 8 character"
            + ", including at least 1 uppercase character" + ", 1 lowercase character" + " and 1 digit.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
