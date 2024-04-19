package com.co.unitravel.infrastructure.adapters.in.rest.controllers.request;

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
}
