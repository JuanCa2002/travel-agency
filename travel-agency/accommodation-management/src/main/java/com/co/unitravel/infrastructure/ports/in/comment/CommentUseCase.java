package com.co.unitravel.infrastructure.ports.in.comment;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CommentUseCase {

    Comment create(Comment comment) throws NotFoundException, BusinessException, JsonProcessingException;

    Comment update(Comment comment) throws NotFoundException;

    Comment updateStatus(Long id) throws NotFoundException;

    List<Comment> getByAccommodation(Long accommodationId);

    List<Comment> getByCustomer(Long customerId);
}
