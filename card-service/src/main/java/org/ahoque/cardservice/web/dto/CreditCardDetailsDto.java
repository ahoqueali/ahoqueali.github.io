package org.ahoque.cardservice.web.dto;

import org.ahoque.cardservice.validator.CreditCardNumberConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreditCardDetailsDto {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Cannot be blank")
    private String name;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Cannot be blank")
    @Size(max = 19, message = "Cannot be more than 19 characters")
    @CreditCardNumberConstraint
    private String cardNumber;

    @NotNull
    @NotBlank
    //TODO: more checks needed for balance
    private String accountBalance;

    public CreditCardDetailsDto(){};

    public CreditCardDetailsDto(final String name, final String cardNumber, final String accountBalance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountBalance() {
        return accountBalance;
    }
}
