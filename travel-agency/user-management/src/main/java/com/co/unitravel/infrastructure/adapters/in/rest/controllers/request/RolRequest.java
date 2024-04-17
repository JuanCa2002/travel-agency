package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import com.co.unitravel.domain.models.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolRequest {

    private String name;

    private List<Long> permissionList;
}
