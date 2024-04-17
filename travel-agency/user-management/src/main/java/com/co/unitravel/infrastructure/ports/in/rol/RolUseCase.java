package com.co.unitravel.infrastructure.ports.in.rol;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Rol;

import java.util.List;

public interface RolUseCase {
    Rol create(Rol rol) throws NotFoundException;

    List<Rol> getAll();

    Rol findById(Long id) throws NotFoundException;
}
