package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.rol.RolErrorCodes;
import com.co.unitravel.application.exceptions.rol.RolNotFoundException;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.infrastructure.adapters.out.database.entities.RolEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.rol.RolMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.RolRepository;
import com.co.unitravel.infrastructure.ports.out.rol.RolPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolAdapter implements RolPort {

    private final RolRepository rolRepository;

    private final RolMapper rolMapper;
    @Override
    public Rol save(Rol rol) {
        RolEntity savedRol = rolRepository.save(rolMapper.domainToEntity(rol));
        return rolMapper.entityToDomain(savedRol);
    }

    @Override
    public List<Rol> findAll() {
        return rolMapper.entitiesToDomains(rolRepository.findAll());
    }

    @Override
    public Rol findById(Long id) throws NotFoundException {
        RolNotFoundException errorNotFound = new RolNotFoundException();
        errorNotFound.addError(RolErrorCodes.ROL_NOT_FOUND, new Object[]{id});
        return rolMapper.entityToDomain(rolRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public boolean existsByName(String name) {
        return rolRepository.existsByName(name);
    }

    @Override
    public void existsById(Long id) throws NotFoundException {
        RolNotFoundException errorNotFound = new RolNotFoundException();
        errorNotFound.addError(RolErrorCodes.ROL_NOT_FOUND, new Object[]{id});
        if(!rolRepository.existsById(id)) throw errorNotFound;
    }

    @Override
    public Rol update(Rol rol) throws NotFoundException {
        RolNotFoundException errorNotFound = new RolNotFoundException();
        errorNotFound.addError(RolErrorCodes.ROL_NOT_FOUND, new Object[]{rol.getId()});
        RolEntity target = rolRepository.findById(rol.getId()).orElseThrow(()-> errorNotFound);
        rolMapper.mergeToEntity(target, rol);
        return rolMapper.entityToDomain(rolRepository.save(target));
    }

    @Override
    public List<Rol> findByIds(List<Long> ids) {
        List<RolEntity> foundRoles = rolRepository.findAllById(ids);
        return rolMapper.entitiesToDomains(foundRoles);
    }
}
