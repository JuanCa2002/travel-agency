package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String name;

    private String lastNames;

    private String email;

    private String password;

    private String documentNumber;

    private String phoneNumber;

    private Long cityId;

    private Integer documentTypeId;

    private List<Long> rolIds;
}
