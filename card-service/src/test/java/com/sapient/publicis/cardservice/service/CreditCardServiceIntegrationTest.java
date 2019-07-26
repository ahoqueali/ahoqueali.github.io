package com.sapient.publicis.cardservice.service;

import com.sapient.publicis.cardservice.domain.CreditCardAccount;
import com.sapient.publicis.cardservice.repository.CreditCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CreditCardServiceIntegrationTest {

    public static final String CARD_NUMBER = "4111111111111111";

    @TestConfiguration
    static class CardServiceTestContextConfiguration{

        @Bean
        public CreditCardService cardService(){
            return new CreditCardService();
        }
    }

    @Autowired
    private CreditCardService creditCardService;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Before
    public void setUp(){

        CreditCardAccount creditCardAccount = new CreditCardAccount("Hoque Ali", CARD_NUMBER, "0.00");
        Mockito.when(creditCardRepository.findByCardNumber(creditCardAccount.getCardNumber())).thenReturn(creditCardAccount);
    }

    @Test
    public void whenValidCardNumber_thenCardAccountShouldBeFound(){

        String cardNumber = CARD_NUMBER;

        CreditCardAccount creditCardAccount = creditCardService.getCardAccountByCardNumber(cardNumber);
        assertThat(creditCardAccount.getCardNumber().equals(cardNumber));
    }

}
