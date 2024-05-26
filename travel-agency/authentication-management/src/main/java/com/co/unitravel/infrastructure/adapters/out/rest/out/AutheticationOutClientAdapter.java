package com.co.unitravel.infrastructure.adapters.out.rest.out;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.client.authentication.AuthenticationOutClientPort;
import com.co.unitravel.infrastructure.ports.out.client.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutheticationOutClientAdapter implements AuthenticationOutClientPort {

    private final UserPort userPort;

    @RabbitListener(queues = AmqpConfigurationConstants.AUTHENTICATION_QUEUE)
    @Override
    public void createNewUser(User user) {
        System.out.println("El usuario que llego fue: " + user.getFirstName());
        userPort.registerUser(user, user.getToken());
    }
}
