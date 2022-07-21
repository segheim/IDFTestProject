package com.idf.idftestproject.web;

import com.idf.idftestproject.exception.ControllerException;
import com.idf.idftestproject.service.CryptocurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping
    public List<String> readAll() throws ControllerException {
        final List<String> cryptocurrenciesSymbols = cryptocurrencyService.findAllCryptocurrencySymbols();
        if (cryptocurrenciesSymbols.isEmpty()) {
            String message = "Available cryptocurrencies are absent";
            logger.warn(message);
            throw new ControllerException(message);
        }
        return cryptocurrenciesSymbols;
    }

    @GetMapping("/{symbol}")
    public Map<String, String> findPriceUsd(@PathVariable String symbol) {
        return cryptocurrencyService.findPriceCryptocurrencyBySymbol(symbol);
    }
}
