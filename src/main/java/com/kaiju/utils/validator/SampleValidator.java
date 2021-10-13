package com.kaiju.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValidator implements
        ConstraintValidator<SampleConstraint, String> {

    private static final String NUM_REX   = ".*[0-9].*";
    private static final String ALPHA_REX = ".*[a-zA-Z].*";

    @Override
    public void initialize(SampleConstraint str) {
    }

    @Override
    public boolean isValid(String str,
                           ConstraintValidatorContext cxt) {
        return str != null && str.matches(NUM_REX) && str.matches(ALPHA_REX);
    }

}
