package com.idf.idftestproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends AbstractEntity{

    @Column(name = "c_symbol")
    private String symbol;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_nameid")
    private String nameid;
    @Column(name = "c_rank")
    private Integer rank;
    @Column(name = "price_usd")
    private BigDecimal priceUsd;
    @Column(name = "c_percent_change_24h")
    private BigDecimal percentChange24h;
    @Column(name = "c_percent_change_1h")
    private BigDecimal percentChange1h;
    @Column(name = "c_percent_change_7d")
    private BigDecimal percentChange7d;
    @Column(name = "c_market_cap_usd")
    private BigDecimal marketCapUsd;
    @Column(name = "c_volume24")
    private BigDecimal volume24;
    @Column(name = "c_volume24_native")
    private BigDecimal volume24Native;
    @Column(name = "c_csupply")
    private BigDecimal cSupply;
    @Column(name = "c_price_btc")
    private BigDecimal priceBtc;
    @Column(name = "c_tsupply")
    private BigDecimal tSupply;
    @Column(name = "c_msupply")
    private BigDecimal mSupply;
}
