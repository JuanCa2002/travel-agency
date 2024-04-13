package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.City;
import com.co.unitravel.infrastructure.adapters.out.database.entities.CityEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.city.CityMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.CityRepository;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
