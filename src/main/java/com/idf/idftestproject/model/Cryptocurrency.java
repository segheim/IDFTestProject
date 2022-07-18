package com.idf.idftestproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@SuperBuilder
@Table(name = "cryptocurrencies")
@Data
@NoArgsConstructor
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Cryptocurrency extends CryptocurrencyCode{

    @Column(name = "price_usd")
    private BigDecimal priceUsd;
}
