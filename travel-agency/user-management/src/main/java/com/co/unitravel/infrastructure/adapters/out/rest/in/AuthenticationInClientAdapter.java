package com.co.unitravel.infrastructure.adapters.out.rest.in;

import com.co.unitravel.domain.models.UserKeycloak;
import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.client.in.AuthenticationInClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationInClientAdapter implements AuthenticationInClientPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void registerNewUser(UserKeycloak user) {
        System.out.println("Usuario que se envia: "+ user.getFirstName());
        rabbitTemplate.convertAndSend(
                AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.AUTHENTICATION_ROUTING_KEY, user);
    }
}
