package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;

    private String password;
}
