package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PageResponse<T> {

    @JsonProperty("registers")
    private T data;

    @JsonProperty("totalAmount")
    private BigInteger total;

}
