package com.idf.idftestproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cryptocurrencies")
@JsonIgnoreProperties
public class Cryptocurrency extends CryptocurrencyCode {

    @Column(name = "price_usd")
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;

    public Cryptocurrency(Long id, String symbol, BigDecimal priceUsd) {
        super(id, symbol);
        this.priceUsd = priceUsd;
    }

    public Cryptocurrency() {
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cryptocurrency that = (Cryptocurrency) o;
        return Objects.equals(priceUsd, that.priceUsd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priceUsd);
    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "priceUsd=" + priceUsd +
                "} " + super.toString();
    }
}
