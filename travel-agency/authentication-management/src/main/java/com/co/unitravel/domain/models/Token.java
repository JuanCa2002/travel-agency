package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private String access_token;

    private String refresh_token;
}
