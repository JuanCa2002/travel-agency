package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import com.co.unitravel.domain.models.enums.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    private Long id;

    private String email;

    private UserStatus status;

    private String phoneNumber;

    private Long cityId;
}
