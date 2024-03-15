package com.dedagroup.springboottraining.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CredentialChecker.class)
public @interface CheckCredentials {

    String message();
    String field();
    String fieldMatch();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        CheckCredentials[] value();
    }
}
