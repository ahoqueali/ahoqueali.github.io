package org.ahoque.core;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal amount;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;

    public Transaction(){}

    public Transaction(final String type, final BigDecimal amount, final Long accountId) {
        this.type = type;
        this.amount = amount;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return "debit".equals(type)? amount.negate(): amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(final Long accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(final LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(createDatetime, that.createDatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, amount, accountId, createDatetime);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", accountId=" + accountId +
                ", createDatetime=" + createDatetime +
                '}';
    }
}
