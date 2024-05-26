package com.co.unitravel.infrastructure.ports.out.client.in;

import com.co.unitravel.domain.models.UserKeycloak;

public interface AuthenticationInClientPort {

    void registerNewUser(UserKeycloak user);
}
