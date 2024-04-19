package com.co.unitravel.infrastructure.ports.out.comment;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Comment;

import java.util.List;

public interface CommentPort {

    Comment save(Comment comment);

    Comment update(Comment comment) throws NotFoundException;

    Comment findById(Long id) throws NotFoundException;

    List<Comment> findByAccommodation(Long accommodationId);

    List<Comment> findByCustomer(Long customerId);
}
