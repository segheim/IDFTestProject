package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.ServiceException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService<Cryptocurrency> {

    private final CryptocurrencyRepository repository;
//    private final DtoConverter<Currency, CurrencyDto> converter;

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cryptocurrency> findAll() {
        return repository.findAll();
    }

    @Override
    public Cryptocurrency findCryptocurrencyBySymbol(String symbol){
//        final Cryptocurrency cryptocurrency= repository.findCryptocurrencyBySymbol(symbol).orElseThrow(() ->
//                new ServiceException("Could not find cryptocurrency " + symbol));
        final Optional cryptocurrency = repository.findCryptocurrencyBySymbol(symbol);
        if (cryptocurrency.isEmpty()) {
            throw new ServiceException("Could not find cryptocurrency " + symbol);
        }
        return (Cryptocurrency) cryptocurrency.get();
    }
}
