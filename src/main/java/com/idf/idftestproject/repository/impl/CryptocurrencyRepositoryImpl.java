package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.exception.RepositoryException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CryptocurrencyRepositoryImpl extends AbstractRepository<Cryptocurrency> implements CryptocurrencyRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected CryptocurrencyRepositoryImpl() {
        super(Cryptocurrency.class);
    }

    @Override
    @Transactional
    public void createCryptocurrency(Cryptocurrency cryptocurrency) {
        final Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(cryptocurrency);
        session.close();
    }

    @Override
    @Transactional
    public void createCryptocurrencies(List<Cryptocurrency> cryptocurrencies) {
        final Session session = entityManager.unwrap(Session.class);
        cryptocurrencies.forEach(session::saveOrUpdate);
        session.close();
    }

    @Override
    public Cryptocurrency findCryptocurrencyBySymbol(String symbol) {
        try {
            final TypedQuery<Cryptocurrency> query = entityManager.createQuery("select u from " +
                    Cryptocurrency.class.getSimpleName() + " u where u.symbol=:symbol", Cryptocurrency.class);
            return query.setParameter("symbol", symbol)
                    .getSingleResult();
        } catch (NoResultException e) {
            String message = "Could not find cryptocurrency by symbol " + symbol;
            logger.warn(message, e);
            throw new RepositoryException(message, e);
        }
    }
}
