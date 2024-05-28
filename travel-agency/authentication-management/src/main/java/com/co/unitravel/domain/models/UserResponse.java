package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String username;

    private Boolean enabled;

    private String token;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean emailVerified;

    private List<Credential> credentials;
}
