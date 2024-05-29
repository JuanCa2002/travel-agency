package com.co.unitravel.infrastructure.adapters.out.rest.out;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.domain.models.UserResponse;
import com.co.unitravel.infrastructure.adapters.out.rest.mappers.UserMapper;
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

    private final UserMapper userMapper;

    @RabbitListener(queues = AmqpConfigurationConstants.AUTHENTICATION_QUEUE)
    @Override
    public void createNewUser(UserResponse user) {
        System.out.println("El usuario que llego fue: " + user.getFirstName());
        String bearerToken = "Bearer " + user.getToken();
        System.out.println("El token que llego fue " + bearerToken);
        System.out.println("Uno de los roles que llego es: "+ user.getRealmRoles().get(0));
        User userToSave = userMapper.responseToDomain(user);
        userToSave.setRealmRoles(user.getRealmRoles());
        System.out.println("Uno de los que hay para guardar es: "+ userToSave.getRealmRoles().get(0));
        userPort.registerUser(userMapper.responseToDomain(user), bearerToken);
    }
}
