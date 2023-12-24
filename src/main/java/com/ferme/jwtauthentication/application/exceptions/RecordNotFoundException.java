package com.ferme.jwtauthentication.application.exceptions;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(UUID id) {
        super("Registro não encontrado com id: " + id);
    }
}
