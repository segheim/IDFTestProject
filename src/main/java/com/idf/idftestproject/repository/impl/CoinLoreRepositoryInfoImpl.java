package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.repository.CoinLoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoinLoreRepositoryInfoImpl implements CoinLoreRepository {

    Logger logger = LoggerFactory.getLogger(CoinLoreRepositoryInfoImpl.class);

    private static final String BASIC_URL = "https://api.coinlore.net/api/ticker/?id=";
    private static final String DELIMITER = ",";

    @Override
    public List<Cryptocurrency> getCoinLoreByAllId(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return Collections.emptyList();
        }
        if (idList.size() > 1) {
            RestTemplate restTemplate = new RestTemplate();
            final String ids = idList.stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining(DELIMITER));
            final Cryptocurrency[] currencies = restTemplate.getForObject(BASIC_URL.concat(ids), Cryptocurrency[].class);
            logger.info("!!!!!!!!!!!!!" + Arrays.toString(currencies));
            return Arrays.asList(currencies);
        }
        return List.of(getCoinLoreById(idList.stream().findFirst().get()));

//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(" https://api.coinlore.net/")
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        final Call<Currency> currencies = retrofitService.getUsers();
//        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + currencies.toString());
//        return

    }

    @Override
    public Cryptocurrency getCoinLoreById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        final Cryptocurrency currency = restTemplate.getForObject(BASIC_URL.concat(id.toString()), Cryptocurrency.class);
        logger.info("!!!!!!!!!!!!!" + currency);
        return currency;
    }
}
