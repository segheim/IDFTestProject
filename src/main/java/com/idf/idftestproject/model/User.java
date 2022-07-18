package com.idf.idftestproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "users")
@Data
//@SuperBuilder
//@Getter
//@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class User extends AbstractEntity{

    @Column(name = "u_name")
    private String name;
    @Column(name = "u_price")
    private BigDecimal price;

}
