package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.ServiceException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService<User> {

    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Autowired
    public UserServiceImpl(CryptocurrencyRepository cryptocurrencyRepository) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        final Optional cryptocurrencyBySymbol = cryptocurrencyRepository.findCryptocurrencyBySymbol(userRequest.getSymbol());
        if (cryptocurrencyBySymbol.isEmpty()) {
            throw new ServiceException("Could not find cryptocurrency " + userRequest.getSymbol());
        }
        final Cryptocurrency cryptocurrency = (Cryptocurrency) cryptocurrencyBySymbol.get();
        User.builder()
                .name(userRequest.getUserName())
                .price(cryptocurrency.getPriceUsd())
                .build();

        return null;
    }
}
