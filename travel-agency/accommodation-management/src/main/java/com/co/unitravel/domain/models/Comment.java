package com.co.unitravel.domain.models;

import com.co.unitravel.domain.models.enums.CommentStatus;
import com.co.unitravel.domain.models.enums.CommentType;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Comment {

    private Long id;

    private Accommodation accommodation;

    private Long customerId;

    private String content;

    private LocalDate commentDate;

    private LocalTime commentTime;

    private LocalDate commentEditDate;

    private LocalTime commentEditTime;

    private Double qualification;

    private CommentType commentType;

    private Comment comment;

    private CommentStatus commentStatus;
}
