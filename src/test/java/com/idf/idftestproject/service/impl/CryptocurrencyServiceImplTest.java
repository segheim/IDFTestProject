package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.RepositoryException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.CryptocurrencyCode;
import com.idf.idftestproject.repository.CoinLoreRepository;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CryptocurrencyServiceImplTest {

    private static final String SYMBOL_BTC = "BTC";
    private static final String SYMBOL_SOL = "SOL";
    private static final long ID_80 = 80l;
    private static final long ID_90 = 90l;
    private List<String> symbols = new ArrayList<>();
    private List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
    private List<Long> ids = new ArrayList<>();

    @MockBean
    private CryptocurrencyRepository cryptocurrencyRepository;
    @MockBean
    private CoinLoreRepository coinloreRepository;
    @MockBean
    private List<CryptocurrencyCode> availableCryptocurrency;


    @Autowired
    private CryptocurrencyServiceImpl cryptocurrencyService;

    @BeforeEach
    public void init() {
        symbols.add(SYMBOL_BTC);
        symbols.add(SYMBOL_SOL);

        ids.add(ID_80);
        ids.add(ID_90);

        availableCryptocurrency.add( new CryptocurrencyCode(ID_80, SYMBOL_BTC));
        availableCryptocurrency.add(new CryptocurrencyCode(ID_90, SYMBOL_SOL));

        cryptocurrencies.add(new Cryptocurrency(ID_80, SYMBOL_BTC, BigDecimal.TEN));
        cryptocurrencies.add(new Cryptocurrency(ID_90, SYMBOL_SOL, BigDecimal.ONE));
    }

    @Test
    public void testRetrieveCryptocurrencyPrice_shouldReturnMap() {

    }
}
