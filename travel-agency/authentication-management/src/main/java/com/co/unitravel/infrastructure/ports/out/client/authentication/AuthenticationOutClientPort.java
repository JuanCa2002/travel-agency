package com.co.unitravel.infrastructure.ports.out.client.authentication;

import com.co.unitravel.domain.models.UserResponse;

public interface AuthenticationOutClientPort {

    void createNewUser(UserResponse user);
}
