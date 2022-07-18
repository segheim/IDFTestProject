package com.idf.idftestproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idf.idftestproject.model.CryptocurrencyCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CurrencyConfiguration {

    private static final File fileWithDefaultCryptocurrencyCode = new File("src/main/resources/availableCryptocurrency.json");

    @Bean
    public List<CryptocurrencyCode> getAvailableCryptocurrency() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(fileWithDefaultCryptocurrencyCode, CryptocurrencyCode[].class));
    }
}
