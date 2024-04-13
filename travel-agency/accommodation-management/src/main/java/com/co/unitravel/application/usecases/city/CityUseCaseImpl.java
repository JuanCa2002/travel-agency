package com.co.unitravel.application.usecases.city;

import com.co.unitravel.application.exceptions.department.DepartmentNotFoundException;
import com.co.unitravel.domain.models.City;
import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.ports.in.city.CityUseCase;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import com.co.unitravel.infrastructure.ports.out.department.DepartmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityUseCaseImpl implements CityUseCase {

    private final CityPort cityPort;

    private final DepartmentPort departmentPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public City create(City city) throws DepartmentNotFoundException {
        Department department = departmentPort.findById(city.getDepartment().getId());
        city.setId(null);
        city.setName(city.getName().toUpperCase());
        city.setDepartment(department);
        return cityPort.save(city);
    }

}
