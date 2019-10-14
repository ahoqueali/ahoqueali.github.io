package org.ahoque.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TransactionDto {

    private Long id;
    private String type;
    private String amount;

    public TransactionDto(){}

    public TransactionDto(final String type, final String amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionDto(final long id, final String type, final String amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @JsonProperty
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
        final TransactionDto that = (TransactionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, amount);
    }
}