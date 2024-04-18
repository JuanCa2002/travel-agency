package com.co.unitravel.application.exceptions.comment;

import com.co.unitravel.application.exceptions.accommodation.AccommodationErrorCodes;
import com.co.unitravel.application.exceptions.general.BusinessException;

public class CommentBusinessException extends BusinessException {

    public CommentBusinessException() {
        super(CommentErrorCodes.COMMENT_ERROR_HEADER, CommentErrorCodes.COMMENT_INVALID_TRANSACTION);
    }
}
