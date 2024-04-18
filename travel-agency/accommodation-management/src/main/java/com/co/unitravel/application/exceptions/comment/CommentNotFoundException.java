package com.co.unitravel.application.exceptions.comment;

import com.co.unitravel.application.exceptions.accommodation.AccommodationErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException() {
        super(CommentErrorCodes.COMMENT_ERROR_HEADER, CommentErrorCodes.COMMENT_INVALID_TRANSACTION);
    }
}
