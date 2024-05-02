package com.co.unitravel.infrastructure.ports.out.userrol;

import com.co.unitravel.domain.models.UserRol;

import java.util.List;

public interface UserRolPort {

    List<UserRol> saveAll(List<UserRol> userRoles);
}
