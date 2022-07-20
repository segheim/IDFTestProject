package com.idf.idftestproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class CryptocurrencyCode {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "symbol")
    @JsonProperty("symbol")
    private String symbol;

    public CryptocurrencyCode(Long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public CryptocurrencyCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptocurrencyCode that = (CryptocurrencyCode) o;
        return Objects.equals(id, that.id) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol);
    }

    @Override
    public String toString() {
        return "CryptocurrencyCode{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
