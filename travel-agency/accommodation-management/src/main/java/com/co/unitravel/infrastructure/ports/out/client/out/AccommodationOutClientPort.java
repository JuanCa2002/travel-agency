package com.co.unitravel.infrastructure.ports.out.client.out;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AccommodationOutClientPort {

    Object getAccommodationById(String id) throws JsonProcessingException;

    Object getAccommodationPriceById(String id) throws JsonProcessingException, NotFoundException;

    Object getAccommodationCapacityById(String id) throws JsonProcessingException, NotFoundException;
}
