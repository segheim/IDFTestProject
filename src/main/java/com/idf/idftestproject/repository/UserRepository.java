package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.User;

import java.util.Optional;

public interface UserRepository<T> {

    void createUser(User user);

    Optional<T> findByUserName(String userName);
}
