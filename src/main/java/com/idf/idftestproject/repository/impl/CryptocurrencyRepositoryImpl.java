package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class CryptocurrencyRepositoryImpl extends AbstractRepository<Cryptocurrency> implements CryptocurrencyRepository<Cryptocurrency> {

    protected CryptocurrencyRepositoryImpl() {
        super(Cryptocurrency.class);
    }

    @Override
    public Optional<Cryptocurrency> findCryptocurrencyBySymbol(String symbol) {
        Cryptocurrency cryptocurrency;
        try {
            final TypedQuery<Cryptocurrency> query = entityManager.createQuery("select u from " +
                    Cryptocurrency.class.getSimpleName() + " u where u.symbol=:symbol", Cryptocurrency.class);
            cryptocurrency = query.setParameter("symbol", symbol)
                    .getSingleResult();
            return Optional.of(cryptocurrency);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
