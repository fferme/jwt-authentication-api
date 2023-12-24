package com.ferme.jwtauthentication.application.exceptions;

public class TokenAuthenticationException extends RuntimeException {
    public TokenAuthenticationException(String msg) {
        super(msg);
    }
}