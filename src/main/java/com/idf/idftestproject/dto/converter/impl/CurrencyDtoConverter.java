package com.idf.idftestproject.dto.converter.impl;

import com.idf.idftestproject.dto.CurrencyDto;
import com.idf.idftestproject.dto.converter.DtoConverter;
import com.idf.idftestproject.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDtoConverter implements DtoConverter<Currency, CurrencyDto> {

    @Override
    public CurrencyDto convertToShortDto(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .symbol(currency.getSymbol())
                .name(currency.getName())
                .build();
    }

    @Override
    public CurrencyDto convertToDto(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .symbol(currency.getSymbol())
                .name(currency.getName())
                .nameid(currency.getNameid())
                .rank(currency.getRank())
                .priceUsd(currency.getPriceUsd())
                .percentChange24h(currency.getPercentChange24h())
                .percentChange1h(currency.getPercentChange1h())
                .percentChange7d(currency.getPercentChange7d())
                .marketCapUsd(currency.getMarketCapUsd())
                .volume24(currency.getVolume24())
                .volume24Native(currency.getVolume24Native())
                .cSupply(currency.getCSupply())
                .priceBtc(currency.getPriceBtc())
                .tSupply(currency.getTSupply())
                .mSupply(currency.getMSupply())
                .build();
    }

    @Override
    public Currency convertToEntity(CurrencyDto currencyDto) {
        return Currency.builder()
                .id(currencyDto.getId())
                .symbol(currencyDto.getSymbol())
                .name(currencyDto.getName())
                .nameid(currencyDto.getNameid())
                .rank(currencyDto.getRank())
                .priceUsd(currencyDto.getPriceUsd())
                .percentChange24h(currencyDto.getPercentChange24h())
                .percentChange1h(currencyDto.getPercentChange1h())
                .percentChange7d(currencyDto.getPercentChange7d())
                .marketCapUsd(currencyDto.getMarketCapUsd())
                .volume24(currencyDto.getVolume24())
                .volume24Native(currencyDto.getVolume24Native())
                .cSupply(currencyDto.getCSupply())
                .priceBtc(currencyDto.getPriceBtc())
                .tSupply(currencyDto.getTSupply())
                .mSupply(currencyDto.getMSupply())
                .build();
    }
}
