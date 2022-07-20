package com.idf.idftestproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idf.idftestproject.model.CryptocurrencyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import task.RetrieveCryptocurrencyPriceTask;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAsync
public class CurrencyConfiguration {

    private static final File fileWithDefaultCryptocurrencyCode = new File("src/main/resources/availableCryptocurrency.json");

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public List<CryptocurrencyCode> getAvailableCryptocurrency() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(fileWithDefaultCryptocurrencyCode, CryptocurrencyCode[].class));
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(TaskExecutor executor) {
        final RetrieveCryptocurrencyPriceTask task = new RetrieveCryptocurrencyPriceTask();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(task);
        task.setDaemon(true);
        return (args) -> {
            executor.execute(task);
        };
    }
}
