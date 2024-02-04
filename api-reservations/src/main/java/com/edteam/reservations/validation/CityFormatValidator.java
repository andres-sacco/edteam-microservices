package com.edteam.reservations.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CityFormatValidator implements ConstraintValidator <CityFormatConstraint, String> {

    @Override
    public void initialize(CityFormatConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field != null &&
                field.length() == 3 &&
                field.matches("[A-Z]+");
    }
}
