package com.idf.idftestproject.service;

import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User createUser(UserRequest userRequest);
}
