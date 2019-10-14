package org.ahoque.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BalanceDto {

    private String balanceAmount;

    public BalanceDto(){}

    public BalanceDto(final String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @JsonProperty
    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(final String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDto that = (BalanceDto) o;
        return Objects.equals(balanceAmount, that.balanceAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceAmount);
    }

    @Override
    public String toString() {
        return "BalanceDto{" +
                "balanceAmount='" + balanceAmount + '\'' +
                '}';
    }
}
