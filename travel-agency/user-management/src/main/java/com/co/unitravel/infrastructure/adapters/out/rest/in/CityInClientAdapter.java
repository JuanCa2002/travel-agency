package com.co.unitravel.infrastructure.adapters.out.rest.in;

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.client.in.CityInClientPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityInClientAdapter implements CityInClientPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public boolean findById(Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String idJ = objectMapper.writeValueAsString(id);
        System.out.println("El id enviado: " + idJ);
        Object response = rabbitTemplate.convertSendAndReceive(
                AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.CITY_ROUTING_KEY, idJ);
        if(response == null){
            return false;
        }
        return (Boolean) response;
    }
}
