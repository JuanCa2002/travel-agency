package com.co.unitravel.infrastructure.ports.in.comment;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Comment;

public interface CommentUseCase {

    Comment create(Comment comment) throws NotFoundException, BusinessException;

    Comment update(Comment comment) throws NotFoundException;

    Comment updateStatus(Long id) throws NotFoundException;
}
