package com.idf.idftestproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserRequest {

    private final String userName;
    private final String symbol;

    public UserRequest(@JsonProperty("username") String userName, @JsonProperty("symbol") String symbol) {
        this.userName = userName;
        this.symbol = symbol;
    }

    public String getUserName() {
        return userName;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, symbol);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userName='" + userName + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
