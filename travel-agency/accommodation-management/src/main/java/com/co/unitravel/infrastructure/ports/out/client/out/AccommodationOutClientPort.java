package com.co.unitravel.infrastructure.ports.out.client.out;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AccommodationOutClientPort {

    Object getAccommodationById(String id) throws JsonProcessingException;
}
