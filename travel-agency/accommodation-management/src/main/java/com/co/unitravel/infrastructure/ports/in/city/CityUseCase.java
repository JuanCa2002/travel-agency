package com.co.unitravel.infrastructure.ports.in.city;

import com.co.unitravel.application.exceptions.city.CityBusinessException;
import com.co.unitravel.application.exceptions.department.DepartmentNotFoundException;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.City;

import java.util.List;

public interface CityUseCase {

    City create(City city) throws NotFoundException, BusinessException;

    City getById(Long id) throws NotFoundException;

    List<City> getByDepartment(Integer departmentId);
}
