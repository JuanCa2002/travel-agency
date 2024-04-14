package com.co.unitravel.infrastructure.adapters.out.rest.out;

import com.co.unitravel.application.exceptions.city.CityErrorCodes;
import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.domain.models.City;
import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import com.co.unitravel.infrastructure.ports.out.client.out.CityOutClientPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityOutClientAdapter implements CityOutClientPort {

    private final RabbitTemplate rabbitTemplate;

    private final CityPort cityPort;

//    @RabbitListener(queues = AmqpConfigurationConstants.CITY_QUEUE)
//    public Object getCityById(String id) throws CityNotFoundException {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Long idF = objectMapper.readValue(id, Long.class);
//            City city = cityPort.findById(idF);
//            System.out.println("La ciudad es esta:"+city.getName());
//            Gson gson = new Gson();
//            String cityJson = gson.toJson(city);
//            System.out.println(cityJson);
//            return cityJson;
//        } catch (CityNotFoundException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            CityNotFoundException errorNotFound = new CityNotFoundException();
//            errorNotFound.addError(CityErrorCodes.CITY_NOT_FOUND, new Object[]{id});
//            throw new CityNotFoundException();
//        }
//    }

    @RabbitListener(queues = AmqpConfigurationConstants.CITY_QUEUE)
    @Override
    public Object getCityById(String id){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Long idF = objectMapper.readValue(id, Long.class);
            return cityPort.validate(idF);
        } catch (Exception ex) {
            return false;
        }
    }
}
