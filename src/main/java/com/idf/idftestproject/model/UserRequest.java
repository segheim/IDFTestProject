package com.idf.idftestproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserRequest {

    private final String userName;
    private final String symbol;

    public UserRequest(@JsonProperty("username") String userName, @JsonProperty("symbol") String symbol) {
        this.userName = userName;
        this.symbol = symbol;
    }
}
