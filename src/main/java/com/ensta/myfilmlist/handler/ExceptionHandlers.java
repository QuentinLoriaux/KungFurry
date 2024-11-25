package com.ensta.myfilmlist.handler;


import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.BindException;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<String>
    handleException(ControllerException exception, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String>
    handleBindException(BindException exception, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}

