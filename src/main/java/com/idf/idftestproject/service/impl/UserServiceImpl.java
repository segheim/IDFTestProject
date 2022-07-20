package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.repository.UserRepository;
import com.idf.idftestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(UserRequest userRequest) {
        final Cryptocurrency cryptocurrencyBySymbol = cryptocurrencyRepository.findCryptocurrencyBySymbol(userRequest.getSymbol());
        final User user = User.builder()
                .name(userRequest.getUserName())
                .symbol(userRequest.getSymbol())
                .price(cryptocurrencyBySymbol.getPriceUsd())
                .build();
        userRepository.createUser(user);
        final User userFromBd = userRepository.findByUserName(userRequest.getUserName());
        return userFromBd;
    }
}
