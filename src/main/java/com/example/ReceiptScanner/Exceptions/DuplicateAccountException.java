package com.example.ReceiptScanner.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateAccountException extends RuntimeException{

    public DuplicateAccountException(String message) {
        super(message);
        HttpStatus.valueOf("This account already exists");
    }
}
