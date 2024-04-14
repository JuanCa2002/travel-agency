package com.co.unitravel.infrastructure.ports.out.city;

import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.domain.models.City;

import java.util.List;

public interface CityPort {

    City save(City city);

    City findById(Long id) throws CityNotFoundException;

    boolean existsByName(String name);

    boolean validate(Long id);

    List<City> findByDepartment(Integer departmentId);

}
