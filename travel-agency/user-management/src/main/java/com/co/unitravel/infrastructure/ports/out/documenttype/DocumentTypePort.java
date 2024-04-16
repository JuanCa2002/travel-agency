package com.co.unitravel.infrastructure.ports.out.documenttype;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.DocumentType;

import java.util.List;

public interface DocumentTypePort {

    DocumentType save(DocumentType documentType);

    DocumentType update(DocumentType documentType) throws NotFoundException;

    DocumentType findById(Integer id) throws NotFoundException;

    List<DocumentType> findAll();

    boolean existsByName(String name);

    boolean existsByKey(String key);
}
