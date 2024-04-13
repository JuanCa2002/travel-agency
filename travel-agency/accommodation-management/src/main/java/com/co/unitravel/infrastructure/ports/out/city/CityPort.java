package com.co.unitravel.infrastructure.ports.out.city;

import com.co.unitravel.domain.models.City;

public interface CityPort {

    City save(City city);
}
