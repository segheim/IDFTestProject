package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    void createUser(User user);

    User findByUserName(String userName);
}
