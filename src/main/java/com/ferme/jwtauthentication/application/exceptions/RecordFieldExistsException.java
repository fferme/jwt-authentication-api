package com.ferme.jwtauthentication.application.exceptions;

public class RecordFieldExistsException extends RuntimeException {
    public RecordFieldExistsException(String fieldName, String fieldValue) {
        super("Já existe um registro com " + fieldName + " = '" + fieldValue + "'");
    }
}