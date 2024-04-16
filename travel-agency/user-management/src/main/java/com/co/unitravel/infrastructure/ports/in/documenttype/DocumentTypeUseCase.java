package com.co.unitravel.infrastructure.ports.in.documenttype;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.DocumentType;

import java.util.List;

public interface DocumentTypeUseCase {

    DocumentType create(DocumentType documentType) throws BusinessException;

    DocumentType update(DocumentType documentType) throws NotFoundException, BusinessException;

    DocumentType getById(Integer id) throws NotFoundException;

    List<DocumentType> getAll();


}
