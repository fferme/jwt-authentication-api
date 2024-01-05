package com.ferme.jwtauthentication.user.enums.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        this.acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                                    .map(Enum::toString)
                                    .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.toString().trim().isEmpty()) {
            return true;
        }

        if (!this.acceptedValues.contains(value.toString())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                       "Só são aceitos os valores " + this.acceptedValues)
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}