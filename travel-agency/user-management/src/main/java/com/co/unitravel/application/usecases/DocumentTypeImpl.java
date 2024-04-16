package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.documenttype.DocumentTypeBusinessException;
import com.co.unitravel.application.exceptions.documenttype.DocumentTypeErrorCodes;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.infrastructure.ports.in.documenttype.DocumentTypeUseCase;
import com.co.unitravel.infrastructure.ports.out.documenttype.DocumentTypePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeImpl implements DocumentTypeUseCase {

    private final DocumentTypePort documentTypePort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DocumentType create(DocumentType documentType) throws BusinessException {
        validateBusinessErrors(documentType);
        String nameU = documentType.getName().toUpperCase();
        String keyU = documentType.getKey().toUpperCase();
        documentType.setName(nameU);
        documentType.setKey(keyU);
        documentType.setId(null);
        return documentTypePort.save(documentType);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DocumentType update(DocumentType documentType) throws NotFoundException, BusinessException {
        validateBusinessErrors(documentType);
        return documentTypePort.update(documentType);
    }

    @Transactional(readOnly = true)
    @Override
    public DocumentType getById(Integer id) throws NotFoundException {
        return documentTypePort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentType> getAll() {
        return documentTypePort.findAll();
    }


    private void validateBusinessErrors(DocumentType documentType) throws DocumentTypeBusinessException {
        DocumentTypeBusinessException error = new DocumentTypeBusinessException();
        String nameU = documentType.getName().toUpperCase();
        String keyU = documentType.getKey().toUpperCase();
        if(documentTypePort.existsByName(nameU)){
            error.addError(DocumentTypeErrorCodes.DOCUMENT_TYPE_NAME_ALREADY_EXISTS, new Object[]{nameU});
            throw error;
        }else if(documentTypePort.existsByKey(keyU)){
            error.addError(DocumentTypeErrorCodes.DOCUMENT_TYPE_KEY_ALREADY_EXISTS, new Object[]{keyU});
            throw error;
        }
    }
}
