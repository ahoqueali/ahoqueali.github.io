package com.sapient.publicis.cardservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.publicis.cardservice.CreditCardAccountApplication;
import com.sapient.publicis.cardservice.domain.CreditCardAccount;
import com.sapient.publicis.cardservice.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardAccountApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-int.properties")
public class CreditCardServiceControllerIntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CreditCardRepository repository;

    @Test
    public void givenCreditCards_whenGetAll_thenReturnCreditCards() throws Exception {

        createCreditCardAccounts();

        mvc.perform(get("/api/cards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cardAccounts[0].name", is("Hoque Ali")))
                .andExpect(jsonPath("$.cardAccounts", hasSize(5)));

    }

    private void createCreditCardAccounts(){
        repository.save(createCardCardAccountEntity("378282246310005"));
        repository.save(createCardCardAccountEntity("371449635398431"));
        repository.save(createCardCardAccountEntity("378734493671000"));
        repository.save(createCardCardAccountEntity("5610591081018250"));
        repository.save(createCardCardAccountEntity("30569309025904"));
    }

    private CreditCardAccount createCardCardAccountEntity(String cardNumber){
        return new CreditCardAccount(
                        "Hoque Ali", cardNumber, "0.00");
    }


}
