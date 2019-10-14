package org.ahoque.core;

import org.ahoque.db.AccountRepository;
import org.ahoque.db.BalanceRepository;
import org.ahoque.db.TransactionRepository;
import org.ahoque.api.TransactionDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public TransactionService(final AccountRepository accountRepository,
                              final TransactionRepository transactionRepository,
                              final BalanceRepository balanceRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
    }

    public TransactionDto save(final String accountNumber, final TransactionDto dto) {

        return accountRepository.findByAccountNumber(accountNumber).map(account -> {

            Transaction savedTransaction = transactionRepository.save(convertToTransaction(dto, account));

            // update account balance
            BigDecimal updatedBalance = balanceRepository.findLatestBalance(account.getId())
                    .map(balance -> balance.calculateNewBalance(savedTransaction))
                    .orElse(savedTransaction.getAmount());

            balanceRepository.save(new Balance(account.getId(), updatedBalance));

            return convertToTransactionDto(savedTransaction);

        }).orElseThrow(IllegalArgumentException::new);

    }

    public List<TransactionDto> getAllTransactions(final String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(account -> transactionRepository.findByAccountId(account.getId()))
                 .orElse(new ArrayList<>())
                 .stream()
                 .map(this::convertToTransactionDto)
                 .collect(Collectors.toList());
    }

    private TransactionDto convertToTransactionDto(final Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount().toPlainString());
    }

    private Transaction convertToTransaction(final TransactionDto dto, final Account account){
        return new Transaction(
                dto.getType(),
                new BigDecimal(dto.getAmount()),
                account.getId());
    }
}
