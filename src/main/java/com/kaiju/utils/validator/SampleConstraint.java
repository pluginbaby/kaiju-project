package com.kaiju.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SampleValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SampleConstraint {

    String message() default "Invalid sample string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
