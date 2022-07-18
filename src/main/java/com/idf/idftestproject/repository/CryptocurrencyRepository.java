package com.idf.idftestproject.repository;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepository<T> {

    List<T> findAll();

    Optional<T> findCryptocurrencyBySymbol(String symbol);
}
