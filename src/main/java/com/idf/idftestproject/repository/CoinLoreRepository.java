package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.Cryptocurrency;

import java.util.List;

public interface CoinLoreRepository {

    List<Cryptocurrency> getCoinLoreByAllId(List<Long> idList);

    Cryptocurrency getCoinLoreById(Long id);
}
