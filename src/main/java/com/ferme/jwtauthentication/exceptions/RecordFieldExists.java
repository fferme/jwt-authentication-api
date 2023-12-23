package com.ferme.jwtauthentication.exceptions;

public class RecordFieldExists extends RuntimeException {
    public RecordFieldExists(String fieldName, String fieldValue) {
        super("Já existe um registro com " + fieldName + " = '" + fieldValue + "'");
    }
}
