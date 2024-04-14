package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinationRequest {

    @NotNull
    private Long cityId;

    @NotNull
    private String description;

    @NotNull
    private String photoUrl;
}
