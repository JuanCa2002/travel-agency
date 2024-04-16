package com.co.unitravel.application.exceptions.general;

import lombok.Getter;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public class ApiException extends Exception {

    private final String message;
    private final String code;
    private final List<String> errors;
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    private static final Locale LOCALE_DEFAULT = Locale.ENGLISH;

    public ApiException(String message, String code) {
        super(message);
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        this.message = getMessage(message, null);
        this.code = getMessage(code, null);
        this.errors = new ArrayList<>();
    }

    public void addError(String error, Object[] params) {
        String errorFound = getMessage(error, params);
        this.errors.add(errorFound);
    }

    public String getMessage(String errorRoute, Object[] params) {
        return messageSource.getMessage(errorRoute, params, LOCALE_DEFAULT);
    }
}
