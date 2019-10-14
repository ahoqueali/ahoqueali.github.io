package org.ahoque.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.ahoque.api.AccountDto;
import org.ahoque.api.TransactionDto;
import org.ahoque.core.AccountService;
import org.ahoque.core.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class AccountsResourceTest {

    private static final AccountService ACCOUNT_SERVICE = mock(AccountService.class);
    private static final AccountDto ACCOUNT_DTO = new AccountDto("MR A H ALI", "12345678", "303030");

    private static final TransactionService TRANSACTION_SERVICE = mock(TransactionService.class);
    private static final TransactionDto TRANSACTION_DTO = new TransactionDto( 1L,"credit", "123");


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AccountResource(
                    ACCOUNT_SERVICE,
                    TRANSACTION_SERVICE))
            .bootstrapLogging(false)
            .build();

    @Before
    public void setup() {

        when(ACCOUNT_SERVICE.create(any())).thenReturn(ACCOUNT_DTO);
        when(ACCOUNT_SERVICE.findByAccountNumber(any())).thenReturn(ACCOUNT_DTO);

        when(TRANSACTION_SERVICE.save(any(), any())).thenReturn(TRANSACTION_DTO);
    }

    @Test
    public void givenAccountDetails_whenPostAccounts_thenShouldCreateNewAccount() {

        Response response = resources.target("/accounts").request().post(Entity.json(ACCOUNT_DTO));
        assertThat(response.getLocation().toString()).contains("/accounts/12345678");
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.readEntity(AccountDto.class)).isEqualTo(ACCOUNT_DTO);
    }

    @Test
    public void givenAccountNumber_whenGet_thenShouldReturnAccountDetails(){

        resources.target("/accounts").request().post(Entity.json(ACCOUNT_DTO));
        Response response = resources.target("/accounts/12345678").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(AccountDto.class)).isEqualTo(ACCOUNT_DTO);
    }

    @Test
    public void givenAccountNumberAndCreditAmount_whenPost_thenSaveTransaction(){

        resources.target("/accounts").request().post(Entity.json(ACCOUNT_DTO));
        Response response = resources.target("/accounts/12345678/transactions")
                .request().post(Entity.json(TRANSACTION_DTO));

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().toString()).contains("/accounts/12345678/transactions/1");

        TransactionDto transactionDto = response.readEntity(TransactionDto.class);
        assertThat(transactionDto).isEqualTo(TRANSACTION_DTO);
    }
    

    @After
    public void tearDown(){
        reset(ACCOUNT_SERVICE);
    }
}
