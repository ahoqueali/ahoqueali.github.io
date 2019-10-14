package org.ahoque.core;

import java.util.Objects;

public class MoneyTransferTransaction {

    private Account from;
    private Account to;
    private String amount;

    public MoneyTransferTransaction(final Account from, final Account to, final String amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(final Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(final Account to) {
        this.to = to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MoneyTransferTransaction that = (MoneyTransferTransaction) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, amount);
    }

    @Override
    public String toString() {
        return "MoneyTransferTransaction{" +
                "from=" + from +
                ", to=" + to +
                ", amount='" + amount + '\'' +
                '}';
    }
}
