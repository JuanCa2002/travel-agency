package com.co.unitravel.domain.models;

import com.co.unitravel.domain.models.enums.AccommodationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Accommodation {

    private Long id;

    private Destination destination;

    private String name;

    private String description;

    private String address;

    private String photosUrls;

    private Integer rating;

    private Integer maximumCapacity;

    private Integer price;

    private AccommodationStatus accommodationStatus;
}
