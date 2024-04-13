package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;

    private String name;

    private DepartmentResponse department;
}
