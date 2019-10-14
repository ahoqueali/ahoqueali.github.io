package org.ahoque.core;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="account_id")
    private Long accountId;
    @Column(name="balance_amount")
    private BigDecimal balanceAmount;
    @Column(name ="create_datetime")
    private LocalDateTime createDateTime;

    public Balance(){}

    public Balance(final Long accountId, final BigDecimal balance) {
        this.accountId = accountId;
        this.balanceAmount = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(final Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(final BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(final LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public BigDecimal calculateNewBalance(Transaction transaction) {
        return this.balanceAmount.add(transaction.getAmount());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Balance balance1 = (Balance) o;
        return Objects.equals(id, balance1.id) &&
                Objects.equals(accountId, balance1.accountId) &&
                Objects.equals(balanceAmount, balance1.balanceAmount) &&
                Objects.equals(createDateTime, balance1.createDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, balanceAmount, createDateTime);
    }
}
