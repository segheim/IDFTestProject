package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.CryptocurrencyCode;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.repository.CoinLoreRepository;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private static final int MAX_PERCENT = 100;
    private static final int SCALE_OF_BIG_DECIMAL = 2;

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;
    @Autowired
    private CoinLoreRepository coinloreRepository;
    @Autowired
    private List<CryptocurrencyCode> availableCryptocurrency;

    @Override
    public List<String> findAllCryptocurrencySymbols() {
        return cryptocurrencyRepository.findAll().stream()
                .map(CryptocurrencyCode::getSymbol)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> findPriceCryptocurrencyBySymbol(String symbol) {
        final Cryptocurrency cryptocurrency = cryptocurrencyRepository.findCryptocurrencyBySymbol(symbol);
        final BigDecimal price = cryptocurrency.getPriceUsd();
        return Map.of("price of " + symbol, price.toString());
    }

    @Override
    public List<Cryptocurrency> retrieveCryptocurrencyPrice() {
        final List<Long> ids = availableCryptocurrency.stream()
                .map(CryptocurrencyCode::getId)
                .collect(Collectors.toList());
        final List<Cryptocurrency> cryptocurrencies = coinloreRepository.getCoinLoreByAllId(ids);
        if (!cryptocurrencies.isEmpty()) {
            cryptocurrencyRepository.createCryptocurrencies(cryptocurrencies);
        }
        return cryptocurrencies;
    }

    @Override
    public Map<User, BigDecimal> calculateChangingCryptocurrency(List<Cryptocurrency> cryptocurrencies,
                                                                 List<User> users) {
        Map<User, BigDecimal> result = new HashMap<>();
        for (User user : users) {
            final Optional<Cryptocurrency> cryptocurrencyOptional = cryptocurrencies.stream()
                    .filter(cryptocurrency -> cryptocurrency.getSymbol().equals(user.getSymbol()))
                    .findFirst();
            if (cryptocurrencyOptional.isPresent()) {
                final BigDecimal percentOfChangingPrice = calculatePercentOfChangingPrice(user.getPrice(),
                        cryptocurrencyOptional.get().getPriceUsd());
                if (percentOfChangingPrice.compareTo(BigDecimal.ONE) > 0) {
                    result.put(user, percentOfChangingPrice);
                }
            }

        }
        return result;
    }

    private BigDecimal calculatePercentOfChangingPrice(BigDecimal priceUser, BigDecimal priceActual) {
        return priceUser.subtract(priceActual).abs()
                .multiply(BigDecimal.valueOf(MAX_PERCENT))
                .divide(priceUser, SCALE_OF_BIG_DECIMAL, RoundingMode.HALF_UP);
    }
}
