package com.sapient.publicis.cardservice.repository;

import com.sapient.publicis.cardservice.domain.CreditCardAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardAccount, Long> {
    CreditCardAccount findByCardNumber(String cardNumber);
}
