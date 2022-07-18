package com.idf.idftestproject.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode()
public class CurrencyDto {

    private Long id;
    private String symbol;
    private String name;
    private String nameid;
    private Integer rank;
    private BigDecimal priceUsd;
    private BigDecimal percentChange24h;
    private BigDecimal percentChange1h;
    private BigDecimal percentChange7d;
    private BigDecimal marketCapUsd;
    private BigDecimal volume24;
    private BigDecimal volume24Native;
    private BigDecimal cSupply;
    private BigDecimal priceBtc;
    private BigDecimal tSupply;
    private BigDecimal mSupply;
}
