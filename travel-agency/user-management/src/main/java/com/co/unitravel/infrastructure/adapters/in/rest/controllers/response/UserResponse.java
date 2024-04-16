package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import com.co.unitravel.domain.models.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String name;

    private String lastNames;

    private String email;

    private String documentNumber;

    private String phoneNumber;

    private Long cityId;

    private UserStatus status;
}
