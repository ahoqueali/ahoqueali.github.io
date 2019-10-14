package org.ahoque;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ahoque.core.*;
import org.ahoque.db.AccountRepository;
import org.ahoque.db.BalanceRepository;
import org.ahoque.db.TransactionRepository;
import org.ahoque.resources.AccountResource;
import org.ahoque.resources.MoneyTransferResource;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }

    @Override
    public String getName() {
        return "Money Transfer Service";
    }

    private final HibernateBundle<MoneyTransferConfiguration> hibernate = new HibernateBundle<MoneyTransferConfiguration>(Account.class, Transaction.class, Balance.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MoneyTransferConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<MoneyTransferConfiguration> bootstrap) {

        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new MigrationsBundle<MoneyTransferConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MoneyTransferConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final MoneyTransferConfiguration configuration,
                    final Environment environment) {

        final AccountRepository accountRepository = new AccountRepository(hibernate.getSessionFactory());
        final BalanceRepository balanceRepository = new BalanceRepository(hibernate.getSessionFactory());
        final TransactionRepository transactionRepository = new TransactionRepository(hibernate.getSessionFactory());

        environment.jersey().register(new AccountResource(
                new AccountService(accountRepository, balanceRepository),
                new TransactionService(accountRepository, transactionRepository, balanceRepository)));

        environment.jersey().register(new MoneyTransferResource(
                new MoneyTransferService(
                        new TransactionService(accountRepository, transactionRepository, balanceRepository))));
    }
}
