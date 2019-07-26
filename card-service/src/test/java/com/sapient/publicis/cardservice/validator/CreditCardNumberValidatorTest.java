package com.sapient.publicis.cardservice.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardNumberValidatorTest {

    @Test
    public void givenValidCreditCardNumber_whenTest_thenReturnTrue(){
        String visaCreditCardNumber = "4222222222222";
        CreditCardNumberValidator validator = new CreditCardNumberValidator();
        assertTrue(validator.isValid(visaCreditCardNumber));
    }

    @Test
    public void givenInValidCreditCardNumber_whenTest_thenReturnFalse(){
        String visaCreditCardNumber = "4222222222221";
        CreditCardNumberValidator validator = new CreditCardNumberValidator();
        assertFalse(validator.isValid(visaCreditCardNumber));
    }
}
