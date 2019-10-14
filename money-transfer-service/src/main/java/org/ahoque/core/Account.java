package org.ahoque.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String accountNumber;
    private String sortcode;

    public Account(){}

    public Account (final String name, final String accountNumber, final String sortcode){
        this.name = name;
        this.accountNumber = accountNumber;
        this.sortcode = sortcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

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
        final Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(sortcode, account.sortcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, accountNumber, sortcode);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", sortcode='" + sortcode + '\'' +
                '}';
    }
}
