package org.ahoque.core;

import org.ahoque.api.MoneyTransferDto;
import org.ahoque.api.TransactionDto;

public class MoneyTransferService {

    public static final String SUCCESSFUL = "successful";
    private TransactionService transactionService;

    public MoneyTransferService(final TransactionService transactionService){
        this.transactionService = transactionService;
    }

    /**
     * NB: the moveMoney method should be executed using @UnitOfWork Annotation so
     * that the execution of the method is done as a transaction
     */
    public MoneyTransferDto moveMoney(final MoneyTransferDto moneyTransferDto) {

        transactionService.save(moneyTransferDto.getFromAccount().getAccountNumber(), new TransactionDto("debit", moneyTransferDto.getAmount()));
        transactionService.save(moneyTransferDto.getToAccount().getAccountNumber(), new TransactionDto("credit", moneyTransferDto.getAmount()));

        return new MoneyTransferDto(
                moneyTransferDto.getFromAccount(),
                moneyTransferDto.getToAccount(),
                moneyTransferDto.getAmount(),
                SUCCESSFUL); //TODO: handle when not successful
    }
}
