package com.sapient.publicis.cardservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sapient.publicis.cardservice.domain.CreditCardAccount;
import com.sapient.publicis.cardservice.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
