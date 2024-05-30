package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    @NotNull
    private Long accommodationId;

    @NotNull
    private Long customerId;

    @NotNull
    private String content;

    private Long commentId;

    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    private Double qualification;
}
