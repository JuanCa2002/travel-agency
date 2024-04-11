package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentRequest {

    @NotNull
    private String name;
}
