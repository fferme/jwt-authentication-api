package com.ferme.jwtauthentication.application.controllers;

import com.ferme.jwtauthentication.application.exceptions.RecordFieldExistsException;
import com.ferme.jwtauthentication.application.exceptions.RecordNotFoundException;
import com.ferme.jwtauthentication.application.exceptions.TokenAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RecordFieldExistsException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleFieldExistsException(RecordFieldExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public String handleAuthenticationException(TokenAuthenticationException ex) {
        return ex.getMessage();
    }

}