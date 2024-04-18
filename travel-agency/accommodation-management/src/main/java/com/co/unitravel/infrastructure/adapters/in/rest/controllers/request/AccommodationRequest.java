package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import com.co.unitravel.domain.models.Destination;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationRequest {

    @NotNull
    private Long destinationId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    private String photosUrls;

    @NotNull
    private Integer maximumCapacity;

    @NotNull
    private Integer price;

}
