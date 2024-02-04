package com.edteam.reservations.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CityFormatValidator implements ConstraintValidator<CityFormatConstraint, String> {

    @Override
    public void initialize(CityFormatConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field != null && field.matches("[A-Z]+") && field.length() == 3;
    }
}
