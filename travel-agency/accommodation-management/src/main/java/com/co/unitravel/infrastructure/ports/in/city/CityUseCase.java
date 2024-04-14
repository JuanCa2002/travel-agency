package com.co.unitravel.infrastructure.ports.in.city;

import com.co.unitravel.application.exceptions.department.DepartmentNotFoundException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.City;

public interface CityUseCase {

    City create(City city) throws DepartmentNotFoundException;

    City getById(Long id) throws NotFoundException;
}
