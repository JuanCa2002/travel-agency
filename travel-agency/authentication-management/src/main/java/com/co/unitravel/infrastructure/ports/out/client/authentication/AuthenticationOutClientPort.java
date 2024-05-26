package com.co.unitravel.infrastructure.ports.out.client.authentication;

import com.co.unitravel.domain.models.User;

public interface AuthenticationOutClientPort {

    void createNewUser(User user);
}
