package com.co.unitravel.infrastructure.ports.in.rol;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.domain.models.RolUpdate;

import java.util.List;

public interface RolUseCase {
    Rol create(Rol rol) throws NotFoundException, BusinessException;

    List<Rol> getAll();

    Rol findById(Long id) throws NotFoundException;

    Rol update(RolUpdate rolUpdate) throws NotFoundException, BusinessException;
}
