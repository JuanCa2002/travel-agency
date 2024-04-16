package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.documenttype.DocumentTypeErrorCodes;
import com.co.unitravel.application.exceptions.documenttype.DocumentTypeNotFoundException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DocumentTypeEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.documenttype.DocumentTypeMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.DocumentTypeRepository;
import com.co.unitravel.infrastructure.ports.out.documenttype.DocumentTypePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentTypeAdapter implements DocumentTypePort {

    private final DocumentTypeMapper documentTypeMapper;

    private final DocumentTypeRepository documentTypeRepository;
    @Override
    public DocumentType save(DocumentType documentType) {
        DocumentTypeEntity savedDocumentType = documentTypeRepository.save(documentTypeMapper.domainToEntity(documentType));
        return documentTypeMapper.entityToDomain(savedDocumentType);
    }

    @Override
    public DocumentType update(DocumentType documentType) throws NotFoundException {
        DocumentTypeNotFoundException errorNotFound = new DocumentTypeNotFoundException();
        errorNotFound.addError(DocumentTypeErrorCodes.DOCUMENT_NOT_FOUND, new Object[]{documentType.getId()});
        DocumentTypeEntity target = documentTypeRepository.findById(documentType.getId()).orElseThrow(()-> errorNotFound);
        documentTypeMapper.mergeToEntity(target, documentType);
        return documentTypeMapper.entityToDomain(documentTypeRepository.save(target));
    }

    @Override
    public DocumentType findById(Integer id) throws NotFoundException {
        DocumentTypeNotFoundException errorNotFound = new DocumentTypeNotFoundException();
        errorNotFound.addError(DocumentTypeErrorCodes.DOCUMENT_NOT_FOUND, new Object[]{id});
        return documentTypeMapper.entityToDomain(documentTypeRepository.findById(id).orElseThrow(()->errorNotFound));
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeMapper.entitiesToDomains(documentTypeRepository.findAll());
    }

    @Override
    public boolean existsByName(String name) {
        return documentTypeRepository.existsByName(name);
    }

    @Override
    public boolean existsByKey(String key) {
        return documentTypeRepository.existsByKey(key);
    }
}
