package com.co.unitravel.application.usecases.comment;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Comment;
import com.co.unitravel.domain.models.enums.CommentType;
import com.co.unitravel.infrastructure.ports.in.comment.CommentUseCase;
import com.co.unitravel.infrastructure.ports.out.comment.CommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentUseCaseImpl implements CommentUseCase {

    private final CommentPort commentPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment create(Comment comment) throws NotFoundException, BusinessException {
        if(comment.getComment().getId() == null){
            comment.setCommentType(CommentType.PADRE);
        }else {
            comment.setCommentType(CommentType.HIJO);
            Comment foundComment = commentPort.findById(comment.getComment().getId());
            comment.setComment(foundComment);
        }
        comment.setId(null);

        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment update(Comment comment) throws NotFoundException {
        return commentPort.update(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment updateStatus(Long id) throws NotFoundException {
        return null;
    }
}
