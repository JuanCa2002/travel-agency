package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentTypeUpdateRequest {

    @NotNull
    private Integer id;

    private String name;

    private String key;
}
