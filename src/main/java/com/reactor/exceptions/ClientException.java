package com.reactor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
