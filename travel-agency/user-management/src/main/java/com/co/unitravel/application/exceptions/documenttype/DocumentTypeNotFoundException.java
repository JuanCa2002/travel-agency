package com.co.unitravel.application.exceptions.documenttype;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class DocumentTypeNotFoundException extends NotFoundException {
    public DocumentTypeNotFoundException() {
        super(DocumentTypeErrorCodes.DOCUMENT_TYPE_ERROR_HEADER, DocumentTypeErrorCodes.DOCUMENT_TYPE_INVALID_TRANSACTION);
    }
}
