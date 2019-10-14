package org.ahoque.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class MoneyTransferDto {

    private AccountDto fromAccount;
    private AccountDto toAccount;
    private String amount;
    private String result;

    public MoneyTransferDto(){}

    public MoneyTransferDto(final AccountDto fromAccount, final AccountDto toAccount, final String amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public MoneyTransferDto(final AccountDto fromAccount,
                            final AccountDto toAccount,
                            final String amount,
                            final String result) {

        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.result = result;
    }

    @JsonProperty
    public AccountDto getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(final AccountDto fromAccount) {
        this.fromAccount = fromAccount;
    }

    @JsonProperty
    public AccountDto getToAccount() {
        return toAccount;
    }

    public void setToAccount(final AccountDto toAccount) {
        this.toAccount = toAccount;
    }

    @JsonProperty
    public String getAmount() {
        return amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    @JsonProperty
    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MoneyTransferDto that = (MoneyTransferDto) o;
        return Objects.equals(fromAccount, that.fromAccount) &&
                Objects.equals(toAccount, that.toAccount) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fromAccount, toAccount, amount, result);
    }

    @Override
    public String toString() {
        return "MoneyTransferDto{" +
                "fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount='" + amount + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
