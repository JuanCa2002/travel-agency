package com.co.unitravel.infrastructure.ports.out.client.out;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserOutClientPort {

    Object getUserById(String id) throws JsonProcessingException;
}
