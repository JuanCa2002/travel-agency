package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolResponse {
    private Long id;

    private String name;

    private List<PermissionResponse> permissionList;
}
