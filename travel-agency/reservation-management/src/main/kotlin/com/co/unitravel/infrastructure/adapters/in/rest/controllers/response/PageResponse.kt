package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger

data class PageResponse<T>(@JsonProperty("registers") var data: T, @JsonProperty("totalAmount")var total: BigInteger)