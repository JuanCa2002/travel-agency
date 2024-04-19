package com.co.unitravel.infrastructure.adapters.in.rest.controllers.response;

import com.co.unitravel.domain.models.enums.CommentStatus;
import com.co.unitravel.domain.models.enums.CommentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CommentResponse {

    private Long id;

    private AccommodationResponse accommodation;

    private Long customerId;

    private String content;

    private LocalDate commentDate;

    private LocalTime commentTime;

    private LocalDate commentEditDate;

    private LocalTime commentEditTime;

    private CommentType commentType;

    private CommentResponse comment;

    private CommentStatus commentStatus;
}
