package com.co.unitravel.application.usecases.comment;

import com.co.unitravel.application.exceptions.comment.CommentNotFoundException;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.Comment;
import com.co.unitravel.domain.models.enums.CommentStatus;
import com.co.unitravel.domain.models.enums.CommentType;
import com.co.unitravel.infrastructure.ports.in.comment.CommentUseCase;
import com.co.unitravel.infrastructure.ports.out.accommodation.AccommodationPort;
import com.co.unitravel.infrastructure.ports.out.client.in.UserInClientPort;
import com.co.unitravel.infrastructure.ports.out.comment.CommentPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentUseCaseImpl implements CommentUseCase {

    private final CommentPort commentPort;

    private final AccommodationPort accommodationPort;

    private final UserInClientPort userInClientPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment create(Comment comment) throws NotFoundException, BusinessException, JsonProcessingException {
        CommentNotFoundException errorNotFound = new CommentNotFoundException();
        errorNotFound.addError(GeneralApiErrorCodes.USER_ACCOMMODATION_NOT_FOUND, new Object[]{comment.getCustomerId()});
        Accommodation accommodation = accommodationPort.findById(comment.getAccommodation().getId());
        if(!userInClientPort.findById(comment.getCustomerId())) throw errorNotFound;
        if(comment.getComment().getId() == null){
            comment.setCommentType(CommentType.PADRE);
            comment.setComment(null);
        }else {
            comment.setCommentType(CommentType.HIJO);
            Comment foundComment = commentPort.findById(comment.getComment().getId());
            comment.setComment(foundComment);
        }
        comment.setId(null);
        accommodation.setNumberVotes(accommodation.getNumberVotes()+1);
        accommodation.setSummationVotes(accommodation.getSummationVotes()+ comment.getQualification());
        accommodation.setRating(accommodation.getSummationVotes() / (double) accommodation.getNumberVotes());
        Accommodation accommodationUpdate = accommodationPort.update(accommodation);
        comment.setAccommodation(accommodationUpdate);
        comment.setCommentStatus(CommentStatus.ACTIVO);
        comment.setCommentDate(LocalDate.now());
        comment.setCommentTime(LocalTime.now());
        return commentPort.save(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment update(Comment comment) throws NotFoundException {
        comment.setCommentStatus(CommentStatus.EDITADO);
        return commentPort.update(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Comment updateStatus(Long id) throws NotFoundException {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setCommentStatus(CommentStatus.INACTIVO);
        return commentPort.update(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByAccommodation(Long accommodationId) {
        return commentPort.findByAccommodation(accommodationId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByCustomer(Long customerId) {
        return commentPort.findByCustomer(customerId);
    }
}
