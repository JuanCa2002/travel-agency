package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolUpdateRequest {

    @NotNull
    private Long id;

    private String name;

    private List<Long> permissionsToAdd;

    private List<Long> permissionsToRemove;
}
