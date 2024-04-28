package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import com.co.unitravel.domain.models.enums.AccommodationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationResponse {

    private Long id;

    private DestinationResponse destination;

    private String name;

    private String description;

    private String address;

    private String photosUrls;

    private Integer rating;

    private Integer maximumCapacity;

    private Integer price;

    private AccommodationStatus accommodationStatus;

    private Long administratorId;
}
