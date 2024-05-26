package com.co.unitravel.infrastructure.adapters.out.rest;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.out.rest.api.UserClient;
import com.co.unitravel.infrastructure.ports.out.client.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserClient userClient;

    @Override
    public void registerUser(User user, String token) {
        userClient.createNewUser(user, token);
    }
}
