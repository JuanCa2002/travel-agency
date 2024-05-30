package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.enums.AccommodationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationUpdateRequest {

    @NotNull
    private Long id;

    private String name;

    private String description;

    private String address;

    private String photosUrls;

    private Integer maximumCapacity;

    private Integer price;

    private AccommodationStatus accommodationStatus;
}
