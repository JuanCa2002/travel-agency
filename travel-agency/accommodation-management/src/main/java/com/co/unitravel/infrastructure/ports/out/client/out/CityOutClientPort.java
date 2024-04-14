package com.co.unitravel.infrastructure.ports.out.client.out;

import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.domain.models.City;

public interface CityOutClientPort {

    Object getCityById(String id) throws CityNotFoundException;

}
