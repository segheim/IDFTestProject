package com.idf.idftestproject.web;

import com.idf.idftestproject.exception.ControllerException;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.model.UserRequest;
import com.idf.idftestproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    @PostMapping
    public User registration(@RequestBody UserRequest userRequest) throws ControllerException {
        try {
            final User user = service.createUser(userRequest);
            return user;
        } catch (Exception e) {
            String message = "Could not create User with name " + userRequest.getUserName();
            logger.warn(message, e);
            throw new ControllerException(message);
        }
    }
}
