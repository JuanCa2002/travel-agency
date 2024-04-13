package com.co.unitravel.infrastructure.config;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.config.responses.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
@RestControllerAdvice
public class HandlerExceptionsConfig {

    private final MessageSource messageSource;

    private static final Locale localeDefault = Locale.ENGLISH;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBusinessException(BusinessException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode(), ex.getErrors());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode(), ex.getErrors());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String code;
        String message;

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            String fieldName = ex.getBindingResult().getFieldErrors().get(0).getField();
            String constraintType = ex.getBindingResult().getFieldErrors().get(0).getCode();

            String error = "";
            switch (Objects.requireNonNull(constraintType)) {
                case "NotNull":
                    error = getMessage(GeneralApiErrorCodes.FIELD_FIELD_NOT_NULL, new Object[]{fieldName});
                    break;
                default:
                    error = getMessage(GeneralApiErrorCodes.GENERIC_ERROR_MESSAGE, null);
                    break;
            }

            code = getMessage(GeneralApiErrorCodes.VALIDATION_CONFIG, null);
            message = getMessage(GeneralApiErrorCodes.INVALID_TRANSACTION, null);
            return new ErrorResponse(code, message, List.of(error));
        }

        code = getMessage(GeneralApiErrorCodes.VALIDATION_CONFIG, null);
        message = getMessage(GeneralApiErrorCodes.INVALID_TRANSACTION, null);
        return new ErrorResponse(code, message, new ArrayList<>());
    }

    private String getMessage(String route, Object[] params) {
        return messageSource.getMessage(route, params, localeDefault);
    }

}
