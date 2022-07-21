package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.RepositoryException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    private User user;
    private Cryptocurrency cryptocurrency;

    @MockBean
    private CryptocurrencyRepository cryptocurrencyRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @BeforeEach
    public void init() {
        user = User.builder()
                .id(1l)
                .name("One")
                .symbol("BTC")
                .price(BigDecimal.TEN)
                .build();
        cryptocurrency = new Cryptocurrency(80l, "BTC", BigDecimal.TEN);
    }

    @Test
    public void testCreateUser_shouldReturnUser() {

        when(cryptocurrencyRepository.findCryptocurrencyBySymbol("BTC")).thenReturn(cryptocurrency);
        when(userRepository.findByUserName(user.getName())).thenReturn(user);

        userRepository.createUser(user);
        verify(userRepository).createUser(user);
    }
}
