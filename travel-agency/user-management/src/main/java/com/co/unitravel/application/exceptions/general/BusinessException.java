package com.co.unitravel.application.exceptions.general;

public class BusinessException extends ApiException{

    public BusinessException(String message, String code) {
        super(message, code);
    }
}
