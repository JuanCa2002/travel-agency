package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {

    private Long id;

    private String name;

    private Department department;
}
