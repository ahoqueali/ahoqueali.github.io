package org.ahoque;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ahoque.api.BalanceDto;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTransferAcceptanceTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-config.yml");

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
    public void givenTwoAccounts_whenTransferMoney_thenTheMoneyShouldBeTransferredfromOneAccountToTheOther(){

        // move money from this account
        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(fixture("fixtures/mr-ahali-account-details.json")));

        // to this account
        client.target(
                String.format("http://localhost:%d/accounts", RULE.getLocalPort()))
                .request()
                .post(Entity.json(fixture("fixtures/ms-sh-account-details.json")));

        // transfer £1000
        client.target(
                String.format("http://localhost:%d/transfer-money", RULE.getLocalPort()))
                .request()
                .post(Entity.json(fixture("fixtures/transfer-money.json")));

        // check for balance of debit account
        Response debitBalanceResponse = client.target(
                String.format("http://localhost:%d/accounts/12345678/balance", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(debitBalanceResponse.readEntity(BalanceDto.class)).isEqualTo(new BalanceDto("-1000.00"));

        Response creditAccountBalanceResponse = client.target(
                String.format("http://localhost:%d/accounts/12345679/balance", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(creditAccountBalanceResponse.readEntity(BalanceDto.class)).isEqualTo(new BalanceDto("1000.00"));

    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-db", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
}