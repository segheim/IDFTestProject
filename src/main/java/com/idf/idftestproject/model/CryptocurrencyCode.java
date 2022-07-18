package com.idf.idftestproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public class CryptocurrencyCode extends AbstractEntity{

    @Column(name = "symbol")
    private String symbol;
}
