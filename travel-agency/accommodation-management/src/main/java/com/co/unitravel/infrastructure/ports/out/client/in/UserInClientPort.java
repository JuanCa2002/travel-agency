package com.co.unitravel.infrastructure.ports.out.client.in;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserInClientPort {

    boolean findById(Long id) throws JsonProcessingException;
}
