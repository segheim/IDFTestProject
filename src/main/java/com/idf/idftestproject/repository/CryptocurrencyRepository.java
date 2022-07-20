package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepository {

    void createCryptocurrency(Cryptocurrency cryptocurrency);

    void createCryptocurrencies(List<Cryptocurrency> cryptocurrencies);

    List<Cryptocurrency> findAll();

    Cryptocurrency findCryptocurrencyBySymbol(String symbol);
}
