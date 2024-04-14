package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DestinationResponse {

    private Long id;

    private CityResponse city;

    private String description;

    private String photoUrl;
}
