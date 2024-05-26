package com.co.unitravel.infrastructure.ports.out.client.user;

import com.co.unitravel.domain.models.User;

public interface UserPort {
    void registerUser(User user, String token);
}
