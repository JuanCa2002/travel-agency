package com.co.unitravel.infrastructure.config.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;

    private List<String> errors;
}
