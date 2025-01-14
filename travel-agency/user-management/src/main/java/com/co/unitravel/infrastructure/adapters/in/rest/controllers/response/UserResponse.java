package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import com.co.unitravel.domain.models.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String name;

    private String lastNames;

    private String email;

    private String documentNumber;

    private String phoneNumber;

    private DocumentTypeResponse documentType;

    private Long cityId;

    private UserStatus status;

    private List<RolResponse> rolList;
}
