package org.ahoque.cardservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class CreditCardAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique=true)
    private String cardNumber;

    private BigDecimal accountBalance;

    public CreditCardAccount(){};

    public CreditCardAccount(final Long id, final String name, final String cardNumber, final String accountBalance){
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.CEILING);
    }

    public CreditCardAccount(final String name, final String cardNumber, final String accountBalance){
        this.name = name;
        this.cardNumber = cardNumber;
        this.accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.CEILING);
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        hash = 31 * hash + (cardNumber == null ? 0 : cardNumber.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        CreditCardAccount creditCardAccount = (CreditCardAccount) o;
        return id == creditCardAccount.id
                && (name.equals(creditCardAccount.name)
                && cardNumber.equals(creditCardAccount.cardNumber));
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}