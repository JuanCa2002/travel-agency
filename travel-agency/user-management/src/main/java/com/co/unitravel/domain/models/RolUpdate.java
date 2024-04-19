package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolUpdate {

    private Long id;

    private String name;

    private List<Permission> permissionsToAdd;

    private List<Permission> permissionsToRemove;
}
