package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.ServiceException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.repository.UserRepository;
import com.idf.idftestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService<User> {

    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(CryptocurrencyRepository cryptocurrencyRepository, UserRepository userRepository) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User createUser(UserRequest userRequest) {
        final Optional cryptocurrencyBySymbol = cryptocurrencyRepository.findCryptocurrencyBySymbol(userRequest.getSymbol());
        if (cryptocurrencyBySymbol.isEmpty()) {
            throw new ServiceException("Could not find cryptocurrency " + userRequest.getSymbol());
        }
        final Cryptocurrency cryptocurrency = (Cryptocurrency) cryptocurrencyBySymbol.get();
        final User user = User.builder()
                .name(userRequest.getUserName())
                .price(cryptocurrency.getPriceUsd())
                .build();
        userRepository.createUser(user);
        final Optional userFromBd = userRepository.findByUserName(userRequest.getUserName());
        if (userFromBd.isEmpty()) {
            throw new ServiceException("Could not finad user " + userRequest.getUserName());
        }
        return (User) userFromBd.get();
    }
}
