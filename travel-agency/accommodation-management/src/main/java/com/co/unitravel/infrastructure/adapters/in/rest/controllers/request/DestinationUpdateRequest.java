package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinationUpdateRequest {

    @NotNull
    private Long id;

    private String description;

    private String photoUrl;
}
