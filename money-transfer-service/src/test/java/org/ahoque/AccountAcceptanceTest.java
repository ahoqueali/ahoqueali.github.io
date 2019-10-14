package org.ahoque;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ahoque.api.AccountDto;
import org.ahoque.api.BalanceDto;
import org.ahoque.api.TransactionDto;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountAcceptanceTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-config.yml");
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @ClassRule
    public static final DropwizardAppRule<MoneyTransferConfiguration> RULE =
            new DropwizardAppRule<MoneyTransferConfiguration>(
                    MoneyTransferApplication.class,
                    CONFIG_PATH,
                    ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));


    private static Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
        client = new JerseyClientBuilder(RULE.getEnvironment()).build("Test Client");
        client.property(ClientProperties.CONNECT_TIMEOUT, 100000);
        client.property(ClientProperties.READ_TIMEOUT, 100000);
    }

    @Test
    public void givenAccountsDetails_whenPost_thenCreateAccountInDb() throws IOException {

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345670", "404050");
        Response response = client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.readEntity(AccountDto.class))
                .isEqualTo(accountDto);

    }

    @Test
    public void givenAccountNumber_whenGet_thenReturnSavedAccountDetails() throws IOException {

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345671", "404050");

        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        Response response = client.target(
                String.format("http://localhost:%d/accounts/12345671", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(AccountDto.class))
                .isEqualTo(accountDto);

    }

    @Test
    public void givenAccountNumber_whenGetTransactions_thenShouldReturnAllTransactions(){

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345672", "404050");

        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        client.target(
                String.format("http://localhost:%d/accounts/12345672/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("credit", "12.50")));

        client.target(
                String.format("http://localhost:%d/accounts/12345672/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("credit", "99.99")));

        Response response = client.target(
                String.format("http://localhost:%d/accounts/12345672/transactions", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.readEntity(List.class).size()).isEqualTo(2);

    }

    @Test
    public void givenAccountHas2CreditTransactions_whenGetBalance_thenShouldReturnUpdatedBalance(){

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345673", "404050");

        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        client.target(
                String.format("http://localhost:%d/accounts/12345673/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("credit", "12.50")));

        client.target(
                String.format("http://localhost:%d/accounts/12345673/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("credit", "99.99")));

        Response response = client.target(
                String.format("http://localhost:%d/accounts/12345673/balance", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.readEntity(BalanceDto.class)).isEqualTo(new BalanceDto("112.49"));

    }

    @Test
    public void givenAccountHas2DebitTransactions_whenGetBalance_thenShouldReturnUpdatedBalance(){

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345674", "404050");

        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        client.target(
                String.format("http://localhost:%d/accounts/12345674/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("debit", "12.50")));

        client.target(
                String.format("http://localhost:%d/accounts/12345674/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("debit", "99.99")));

        Response response = client.target(
                String.format("http://localhost:%d/accounts/12345674/balance", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.readEntity(BalanceDto.class)).isEqualTo(new BalanceDto("-112.49"));

    }

    @Test
    public void givenAccountHasOneCreditAndOneDebitTransactions_whenGetBalance_thenShouldReturnUpdatedBalance(){

        AccountDto accountDto = new AccountDto("MR AH ALI", "12345675", "404050");

        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(accountDto));

        client.target(
                String.format("http://localhost:%d/accounts/12345675/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("debit", "12.50")));

        client.target(
                String.format("http://localhost:%d/accounts/12345675/transactions", RULE.getLocalPort()))
                .request()
                .post(Entity.json(new TransactionDto("credit", "99.99")));

        Response response = client.target(
                String.format("http://localhost:%d/accounts/12345675/balance", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.readEntity(BalanceDto.class)).isEqualTo(new BalanceDto("87.49"));

    }


    private static String createTempFile() {
        try {
            return File.createTempFile("test-db", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
