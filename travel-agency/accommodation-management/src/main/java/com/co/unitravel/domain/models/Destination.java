package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Destination {

    private Long id;

    private City city;

    private String description;

    private String photoUrl;

}
