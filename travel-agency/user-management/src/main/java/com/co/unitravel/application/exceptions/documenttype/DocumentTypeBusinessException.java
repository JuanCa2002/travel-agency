package com.co.unitravel.application.exceptions.documenttype;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class DocumentTypeBusinessException extends BusinessException {
    public DocumentTypeBusinessException() {
        super(DocumentTypeErrorCodes.DOCUMENT_TYPE_ERROR_HEADER, DocumentTypeErrorCodes.DOCUMENT_TYPE_INVALID_TRANSACTION);
    }
}
