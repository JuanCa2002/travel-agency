package com.co.unitravel.application.exceptions.general;

public class NotFoundException extends ApiException{
    public NotFoundException(String message, String code) {
        super(message, code);
    }
}
