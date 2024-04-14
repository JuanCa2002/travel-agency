package com.co.unitravel.infrastructure.adapters.out.rest.`in`.responses

import com.fasterxml.jackson.annotation.JsonProperty


data class CityResponse(
    @JsonProperty("id")
    val id: Int?,

    @JsonProperty("name")
    val name: String?,

    @JsonProperty("department")
    val department: DepartmentResponse?
)