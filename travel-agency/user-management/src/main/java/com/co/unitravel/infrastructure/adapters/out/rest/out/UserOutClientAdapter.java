package com.co.unitravel.infrastructure.adapters.out.rest.out;

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.client.out.UserOutClientPort;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserOutClientAdapter implements UserOutClientPort {

    private final UserPort userPort;

    @RabbitListener(queues = AmqpConfigurationConstants.USER_QUEUE)
    @Override
    public Object getUserById(String id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long idF = objectMapper.readValue(id, Long.class);
        return userPort.existsById(idF);
    }
}
