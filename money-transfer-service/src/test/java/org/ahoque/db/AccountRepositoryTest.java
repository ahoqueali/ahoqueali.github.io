package org.ahoque.db;

import io.dropwizard.testing.junit.DAOTestRule;
import org.ahoque.core.Account;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryTest {

    @Rule
    public DAOTestRule database = DAOTestRule.newBuilder()
            .addEntityClass(Account.class).build();

    private AccountRepository accountRepository;

    @Before
    public void setup(){
        accountRepository = new AccountRepository(database.getSessionFactory());
    }

    @Test
    public void givenAnAccountDetails_whenSave_thenShouldPersistAccountIntoDb(){

        Account account = new Account("MR A H ALI", "12345678", "404040");
        Account savedAccount = database.inTransaction(() -> accountRepository.save(account));

        assertThat(savedAccount.getId()).isEqualTo(1l);
    }

}
