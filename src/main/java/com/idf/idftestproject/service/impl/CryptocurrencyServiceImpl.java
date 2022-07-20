package com.idf.idftestproject.service.impl;

import com.idf.idftestproject.exception.ServiceException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.CryptocurrencyCode;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.repository.CoinLoreRepository;
import com.idf.idftestproject.repository.CryptocurrencyRepository;
import com.idf.idftestproject.service.CryptocurrencyService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;
    @Autowired
    private CoinLoreRepository coinloreRepository;
    @Autowired
    private List<CryptocurrencyCode> availableCryptocurrency;

    @Override
    public List<String> findAll() {
        return cryptocurrencyRepository.findAll().stream()
                .map(cryptocurrency -> cryptocurrency.getSymbol())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> findPriceCryptocurrencyBySymbol(String symbol) {
        final Cryptocurrency cryptocurrency = cryptocurrencyRepository.findCryptocurrencyBySymbol(symbol);
        final BigDecimal price = cryptocurrency.getPriceUsd();
        return Map.of("price of " + symbol, price.toString());
    }

    @Override
    @Transactional
    public List<Cryptocurrency> retrieveCryptocurrencyPrice() {
        final List<Long> ids = availableCryptocurrency.stream()
                .map(cryptocurrencyCode -> cryptocurrencyCode.getId())
                .collect(Collectors.toList());
        final List<Cryptocurrency> cryptocurrencies = coinloreRepository.getCoinLoreByAllId(ids);
        cryptocurrencyRepository.createCryptocurrencies(cryptocurrencies);
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
                if (percentOfChangingPrice.compareTo(BigDecimal.ONE) == 1) {
                    result.put(user, percentOfChangingPrice);
                }
            }

        }
        return result;
    }

    private BigDecimal calculatePercentOfChangingPrice(BigDecimal priceUser, BigDecimal priceActual) {
        return priceUser.subtract(priceActual).abs().multiply(BigDecimal.valueOf(100)).divide(priceUser);
    }
}
