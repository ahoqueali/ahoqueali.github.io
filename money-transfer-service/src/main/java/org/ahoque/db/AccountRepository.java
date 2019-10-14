package org.ahoque.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.ahoque.core.Account;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class AccountRepository extends AbstractDAO<Account> {

    public AccountRepository(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Account save(Account account){
        return persist(account);
    }

    public Account findById(Long id){
        return get(id);
    }

    public Optional<Account> findByAccountNumber(String accountNumber){
        Criteria c = criteria().add(Restrictions.eq("accountNumber", accountNumber));
        return Optional.ofNullable(uniqueResult(c));
    }

    public List<Account> findAll(){
        return list(namedQuery("org.ahoque.core.Account.findAll"));
    }
}
