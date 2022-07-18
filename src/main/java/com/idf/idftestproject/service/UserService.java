package com.idf.idftestproject.service;

import com.idf.idftestproject.model.UserRequest;

public interface UserService<T> {

    T createUser(UserRequest userRequest);
}
