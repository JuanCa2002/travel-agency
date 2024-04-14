package com.co.unitravel.application.usecases.city;

import com.co.unitravel.application.exceptions.city.CityBusinessException;
import com.co.unitravel.application.exceptions.city.CityErrorCodes;
import com.co.unitravel.application.exceptions.department.DepartmentNotFoundException;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.City;
import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.ports.in.city.CityUseCase;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import com.co.unitravel.infrastructure.ports.out.department.DepartmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityUseCaseImpl implements CityUseCase {

    private final CityPort cityPort;

    private final DepartmentPort departmentPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public City create(City city) throws NotFoundException, BusinessException {
        Department department = departmentPort.findById(city.getDepartment().getId());
        CityBusinessException error = new CityBusinessException();
        error.addError(CityErrorCodes.CITY_NAME_ALREADY_EXISTS, null);
        if(cityPort.existsByName(city.getName().toUpperCase())) throw error;
        city.setId(null);
        city.setName(city.getName().toUpperCase());
        city.setDepartment(department);
        return cityPort.save(city);
    }

    @Transactional(readOnly = true)
    @Override
    public City getById(Long id) throws NotFoundException {
        return cityPort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<City> getByDepartment(Integer departmentId) {
        return cityPort.findByDepartment(departmentId);
    }

}
