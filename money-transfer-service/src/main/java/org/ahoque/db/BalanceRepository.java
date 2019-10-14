package org.ahoque.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.ahoque.core.Balance;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public class BalanceRepository extends AbstractDAO<Balance> {

    public BalanceRepository(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Balance findById(Long id){
        return get(id);
    }

    public Balance save(Balance balance){
        return persist(balance);
    }

    public Optional<Balance> findLatestBalance(Long accountId){
        Criteria c = criteria().add(Restrictions.eq("accountId", accountId));
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        return Optional.ofNullable(uniqueResult(c));
    }
}
