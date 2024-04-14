package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.city.CityErrorCodes;
import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.domain.models.City;
import com.co.unitravel.infrastructure.adapters.out.database.entities.CityEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.city.CityMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.CityRepository;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityAdapter implements CityPort {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Override
    public City save(City city) {
        CityEntity savedCity = cityRepository.save(cityMapper.domainToEntity(city));
        return cityMapper.entityToDomain(savedCity);
    }

    @Override
    public City findById(Long id) throws CityNotFoundException {
        CityNotFoundException errorNotFound = new CityNotFoundException();
        errorNotFound.addError(CityErrorCodes.CITY_NOT_FOUND,new Object[]{id});
        return cityMapper.entityToDomain(cityRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public boolean existsByName(String name) {
        return cityRepository.existsByName(name);
    }

    @Override
    public boolean validate(Long id){
        CityEntity city = cityRepository.findById(id).orElse(null);
        return city!= null;
    }

    @Override
    public List<City> findByDepartment(Integer departmentId) {
        return cityMapper.entitiesToDomains(cityRepository.findByDepartment(departmentId));
    }
}
