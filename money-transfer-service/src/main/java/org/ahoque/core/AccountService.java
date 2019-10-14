package org.ahoque.core;

import org.ahoque.api.AccountDto;
import org.ahoque.api.BalanceDto;
import org.ahoque.db.AccountRepository;
import org.ahoque.db.BalanceRepository;

import java.util.Optional;

public class AccountService {

    private AccountRepository accountRepository;
    private BalanceRepository balanceRepository;

    public AccountService(final AccountRepository accountRepository,
                          final BalanceRepository balanceRepository){

        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    public AccountDto create(final AccountDto accountDto) throws IllegalArgumentException{

        Account newAccount = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
                .map(account -> {
                    account.setName(accountDto.getName());
                    account.setSortcode(accountDto.getSortcode());
                    return account;
                }).orElse(new Account(
                        accountDto.getName(),
                        accountDto.getAccountNumber(),
                        accountDto.getSortcode()));

        return Optional.ofNullable(accountRepository.save(newAccount))
                .map(account -> new AccountDto(
                        account.getName(),
                        account.getAccountNumber(),
                        account.getSortcode())).orElseThrow(IllegalArgumentException::new);
    }

    public AccountDto findByAccountNumber(final String number) {

        return accountRepository.findByAccountNumber(number)
                .map(account -> new AccountDto(
                        account.getName(),
                        account.getAccountNumber(),
                        account.getSortcode())).orElseThrow(IllegalArgumentException::new);
    }

    public BalanceDto getBalance(final String accountNumber) {

        BalanceDto balanceDto = accountRepository.findByAccountNumber(accountNumber)
                .map(account -> balanceRepository.findLatestBalance(account.getId())
                        .map(balance ->
                                new BalanceDto(
                                        balance.getBalanceAmount()
                                                .toPlainString()))
                        .orElse(new BalanceDto("0.00")))
                .orElseThrow(IllegalArgumentException::new);

        return balanceDto;
    }
}
