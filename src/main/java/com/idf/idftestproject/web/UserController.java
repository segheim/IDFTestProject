package com.idf.idftestproject.web;

import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User registration(@RequestBody UserRequest userRequest) {
        return (User) service.createUser(userRequest);
    }
}
