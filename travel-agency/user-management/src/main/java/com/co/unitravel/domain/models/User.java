package com.co.unitravel.domain.models;

import com.co.unitravel.domain.models.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;

    private String name;

    private String lastNames;

    private String email;

    private String documentNumber;

    private String phoneNumber;

    private String password;

    private Long cityId;

    private UserStatus status;

    private DocumentType documentType;
}
