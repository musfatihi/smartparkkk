package com.smartpark.application.validation;


import com.smartpark.application.dto.client.PasswordForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {


    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        PasswordForm passwordForm = (PasswordForm) o;
        return passwordForm.getPassword().equals(passwordForm.getConfirmedPassword());
    }
}
