package com.sapient.publicis.cardservice.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.publicis.cardservice.domain.CreditCardAccount;
import com.sapient.publicis.cardservice.service.CreditCardService;
import com.sapient.publicis.cardservice.web.dto.CreditCardDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CreditCardServiceController {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/cards")
    public ResponseEntity<JsonNode> createCreditCardAccount(@Valid @RequestBody CreditCardDetailsDto creditCardDetailsDto) throws URISyntaxException {
        CreditCardAccount creditCardAccount = convertToEntity(creditCardDetailsDto);
        CreditCardAccount savedCreditCardAccount = creditCardService.save(creditCardAccount);
        return ResponseEntity
                .created(URI.create("/api/cards/" + savedCreditCardAccount.getId()))
                .body(convertToJson(savedCreditCardAccount));
    }

    @GetMapping("/cards")
    public ResponseEntity<Map<String,List<JsonNode>>> creditCardAccounts(){

        List<JsonNode> creditCardAccounts = new ArrayList<>();
        creditCardService.getAllCreditCardAccounts()
                .forEach(account -> creditCardAccounts.add(convertToJson(account)));

        Map<String,List<JsonNode>> result = new HashMap<>();
        result.put("cardAccounts",creditCardAccounts );
        return ResponseEntity.ok()
                .body(result);
    }

    private CreditCardAccount convertToEntity(CreditCardDetailsDto creditCardDetailsDto){
        return new CreditCardAccount(
                creditCardDetailsDto.getName(),
                creditCardDetailsDto.getCardNumber(),
                creditCardDetailsDto.getAccountBalance());
    }

    private JsonNode convertToJson(CreditCardAccount creditCardAccount){
        CreditCardDetailsDto creditCardDetailsDto = new CreditCardDetailsDto(
                creditCardAccount.getName(),
                creditCardAccount.getCardNumber(),
                creditCardAccount.getAccountBalance().toPlainString());
        return mapper.convertValue(creditCardDetailsDto, JsonNode.class);
    }

}
