package com.idf.idftestproject.service;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CryptocurrencyService {

    List<String> findAll();

    Map<String, String> findPriceCryptocurrencyBySymbol(String symbol);

    List<Cryptocurrency> retrieveCryptocurrencyPrice();

    Map<User, BigDecimal> calculateChangingCryptocurrency(List<Cryptocurrency> cryptocurrencies,
                                                          List<User> users);
}
