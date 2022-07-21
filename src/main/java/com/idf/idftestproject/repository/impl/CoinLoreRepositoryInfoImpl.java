package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.repository.CoinLoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
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
            final Cryptocurrency[] currencies;
            try {
                RestTemplate restTemplate = new RestTemplate();
                final String ids = idList.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(DELIMITER));
                currencies = restTemplate.getForObject(BASIC_URL.concat(ids), Cryptocurrency[].class);
                return Arrays.asList(currencies);
            } catch (RestClientException e) {
                String message = "CoinLore is not available now";
                logger.warn(message, e);
                return Collections.emptyList();
            }
        }
        return List.of(getCoinLoreById(idList.stream().findFirst().get()));
    }

    @Override
    public Cryptocurrency getCoinLoreById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        final Cryptocurrency currency = restTemplate.getForObject(BASIC_URL.concat(id.toString()), Cryptocurrency.class);
        logger.info("!!!!!!!!!!!!!" + currency);
        return currency;
    }
}
