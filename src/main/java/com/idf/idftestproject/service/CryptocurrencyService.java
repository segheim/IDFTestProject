package com.idf.idftestproject.service;

import java.util.List;

public interface CryptocurrencyService<T>{

    List<T> findAll();

    T findCryptocurrencyBySymbol(String symbol) throws Throwable;
}
