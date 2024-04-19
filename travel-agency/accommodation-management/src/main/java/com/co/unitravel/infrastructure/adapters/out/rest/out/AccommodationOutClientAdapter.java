package com.co.unitravel.infrastructure.adapters.out.rest.out;

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.accommodation.AccommodationPort;
import com.co.unitravel.infrastructure.ports.out.client.out.AccommodationOutClientPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccommodationOutClientAdapter implements AccommodationOutClientPort {

    private final AccommodationPort accommodationPort;

    @RabbitListener(queues = AmqpConfigurationConstants.ACCOMMODATION_QUEUE)
    @Override
    public Object getAccommodationById(String id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long idF = objectMapper.readValue(id, Long.class);
        return accommodationPort.existsById(idF);
    }
}
