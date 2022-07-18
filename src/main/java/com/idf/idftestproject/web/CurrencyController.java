package com.idf.idftestproject.web;

import com.idf.idftestproject.dto.CurrencyDto;
import com.idf.idftestproject.exception.ControllerException;
import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.CryptocurrencyCode;
import com.idf.idftestproject.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CryptocurrencyService service;
    @Autowired
    private List<CryptocurrencyCode> availableCryptocurrency;

    @Autowired
    public CurrencyController(CryptocurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public List<CryptocurrencyCode> readAll() throws ControllerException {
        if (availableCryptocurrency.isEmpty()) {
            throw new ControllerException("Cryptocurrency list is missing");
        }
        return availableCryptocurrency;
    }

    @GetMapping("/{symbol}")
    public Cryptocurrency findPriceUsd(@PathVariable String symbol) throws Throwable {
        return (Cryptocurrency) service.findCryptocurrencyBySymbol(symbol);
    }
}
