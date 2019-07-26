package org.ahoque.cardservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumberConstraint, String> {

    @Override
    public void initialize(CreditCardNumberConstraint creditCardNumberConstraint) {
    }

    @Override
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext ctx) {
        return isValid(creditCardNumber);
    }

    public boolean isValid(String creditCardNumber){

        boolean valid = false;

        int[] ints = new int[creditCardNumber.length()];

        for (int i = 0; i < creditCardNumber.length(); i++) {
            ints[i] = Integer.parseInt(creditCardNumber.substring(i, i + 1));
        }

        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }

        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }

        if (sum % 10 == 0) {
            valid = true;
        }

        return valid;
    }
}