package org.ahoque.cardservice.repository;

import org.ahoque.cardservice.domain.CreditCardAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardAccount, Long> {
    CreditCardAccount findByCardNumber(String cardNumber);
}
