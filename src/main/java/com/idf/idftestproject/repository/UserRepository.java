package com.idf.idftestproject.repository;

import com.idf.idftestproject.model.User;

public interface UserRepository<T> {

    T createUser(User user);
}
