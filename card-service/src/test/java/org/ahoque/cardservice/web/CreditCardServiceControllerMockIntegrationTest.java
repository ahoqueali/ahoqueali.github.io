package org.ahoque.cardservice.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ahoque.cardservice.domain.CreditCardAccount;
import org.ahoque.cardservice.service.CreditCardService;
import org.ahoque.cardservice.web.dto.CreditCardDetailsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardServiceController.class)
public class CreditCardServiceControllerMockIntegrationTest {

    private static final String CARD_HOLDERS_NAME = "Hoque Ali";
    private static final String VALID_CARD_NUMBER = "4111111111111111";
    private static final String INVALID_CARD_NUMBER = "4111111111111112";
    private static final String ACCOUNT_BALANCE = "0.00";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreditCardService creditCardService;

    @Test
    public void givenCrediCard_whenPostCardDetails_thenStatus201() throws Exception {

        CreditCardAccount newCard = new CreditCardAccount(CARD_HOLDERS_NAME, VALID_CARD_NUMBER, ACCOUNT_BALANCE);
        CreditCardAccount savedCard = new CreditCardAccount(1L, CARD_HOLDERS_NAME, VALID_CARD_NUMBER, ACCOUNT_BALANCE);
        given(creditCardService.save(newCard)).willReturn(savedCard);

        CreditCardDetailsDto creditCardDetailsDto = new CreditCardDetailsDto(CARD_HOLDERS_NAME, VALID_CARD_NUMBER, ACCOUNT_BALANCE);
        mvc.perform(post("/api/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.convertValue(creditCardDetailsDto, JsonNode.class).toString())
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/api/cards/1"))
                .andExpect(jsonPath("$.name", is(CARD_HOLDERS_NAME)))
                .andExpect(jsonPath("$.cardNumber", is(VALID_CARD_NUMBER)));
    }

    @Test
    public void givenInvalidCrediCard_whenPostCardDetails_thenStatus400() throws Exception {

        CreditCardDetailsDto creditCardDetailsDto = new CreditCardDetailsDto(CARD_HOLDERS_NAME, INVALID_CARD_NUMBER, ACCOUNT_BALANCE);
        mvc.perform(post("/api/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.convertValue(creditCardDetailsDto, JsonNode.class).toString())
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }
}
