package com.co.unitravel.infrastructure.ports.out.rol;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Rol;

import java.util.List;

public interface RolPort {
    Rol save(Rol rol);

    List<Rol> findAll();

    Rol findById(Long id) throws NotFoundException;

    boolean existsByName(String name);

    Rol update(Rol rol) throws NotFoundException;
}
