package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.Cryptocurrency;

import java.util.List;

public interface CryptocurrencyRepository {

    void createCryptocurrency(Cryptocurrency cryptocurrency);

    void createCryptocurrencies(List<Cryptocurrency> cryptocurrencies);

    List<Cryptocurrency> findAll();

    Cryptocurrency findCryptocurrencyBySymbol(String symbol);
}
