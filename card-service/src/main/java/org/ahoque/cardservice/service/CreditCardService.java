package org.ahoque.cardservice.service;

import org.ahoque.cardservice.domain.CreditCardAccount;
import org.ahoque.cardservice.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    @Autowired
    public CreditCardRepository creditCardRepository;

    public CreditCardAccount getCardAccountByCardNumber(final String cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber);
    }

    public CreditCardAccount save(final CreditCardAccount creditCardAccount) {
        return creditCardRepository.save(creditCardAccount);
    }

    public Iterable<CreditCardAccount> getAllCreditCardAccounts() {
        return creditCardRepository.findAll();
    }
}
