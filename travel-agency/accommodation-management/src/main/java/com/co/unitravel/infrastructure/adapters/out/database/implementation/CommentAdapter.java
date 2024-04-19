package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.comment.CommentErrorCodes;
import com.co.unitravel.application.exceptions.comment.CommentNotFoundException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Comment;
import com.co.unitravel.infrastructure.adapters.out.database.entities.CommentEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.comment.CommentMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.CommentRepository;
import com.co.unitravel.infrastructure.ports.out.comment.CommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort {

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentMapper.entityToDomain(commentRepository.save(commentMapper.domainToEntity(comment)));
    }

    @Override
    public Comment update(Comment comment) throws NotFoundException {
        CommentNotFoundException errorNotFound = new CommentNotFoundException();
        errorNotFound.addError(CommentErrorCodes.COMMENT_NOT_FOUND, new Object[]{comment.getId()});
        CommentEntity target = commentRepository.findById(comment.getId()).orElseThrow(()->errorNotFound);
        commentMapper.mergeToEntity(target, comment);
        return commentMapper.entityToDomain(commentRepository.save(target));
    }

    @Override
    public Comment findById(Long id) throws NotFoundException {
        CommentNotFoundException errorNotFound = new CommentNotFoundException();
        errorNotFound.addError(CommentErrorCodes.COMMENT_NOT_FOUND, new Object[]{id});
        return commentMapper.entityToDomain(commentRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public List<Comment> findByAccommodation(Long accommodationId) {
        return commentMapper.entitiesToDomains(commentRepository.findByAccommodation(accommodationId));
    }

    @Override
    public List<Comment> findByCustomer(Long customerId) {
        return commentMapper.entitiesToDomains(commentRepository.findByCustomer(customerId));
    }
}
