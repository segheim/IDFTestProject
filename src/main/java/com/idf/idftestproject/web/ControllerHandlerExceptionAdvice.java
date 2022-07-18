package com.idf.idftestproject.web;

import com.idf.idftestproject.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ControllerHandlerExceptionAdvice {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Object> handleException(ControllerException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", e.getMessage());
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
