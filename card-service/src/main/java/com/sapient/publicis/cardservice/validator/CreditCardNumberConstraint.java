package com.sapient.publicis.cardservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardNumberConstraint {
    String message() default "Invalid Credit Card Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}