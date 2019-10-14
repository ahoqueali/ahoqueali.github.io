package org.ahoque.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccountDto {

    private String name;
    private String accountNumber;
    private String sortcode;

    public AccountDto(){}

    public AccountDto(final String name, final String accountNumber, final String sortcode){
        this.name = name;
        this.accountNumber = accountNumber;
        this.sortcode = sortcode;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @JsonProperty
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty
    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(final String sortcode) {
        this.sortcode = sortcode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AccountDto that = (AccountDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(sortcode, that.sortcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, accountNumber, sortcode);
    }
}
