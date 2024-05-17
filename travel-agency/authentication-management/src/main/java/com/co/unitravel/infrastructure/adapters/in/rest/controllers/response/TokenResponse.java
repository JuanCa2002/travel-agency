package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String access_token;

    private String refresh_token;
}
