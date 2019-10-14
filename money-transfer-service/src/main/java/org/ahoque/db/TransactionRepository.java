package org.ahoque.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.ahoque.core.Transaction;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TransactionRepository extends AbstractDAO<Transaction> {

    public TransactionRepository(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Transaction save(final Transaction transaction) {
        return persist(transaction);
    }

    public List<Transaction> findByAccountId(Long id){
        Criteria c = criteria().add(Restrictions.eq("accountId", id));
        return list(c);
    }
}
