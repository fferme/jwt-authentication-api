package com.ferme.jwtauthentication.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ferme.jwtauthentication.application.exceptions.RecordFieldExists;
import com.ferme.jwtauthentication.application.exceptions.RecordNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RecordFieldExists.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleFieldExistsException(RecordFieldExists ex) {
        return ex.getMessage();
    }
    
}
