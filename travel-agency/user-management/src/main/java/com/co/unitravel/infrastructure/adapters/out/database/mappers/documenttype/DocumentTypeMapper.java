package com.co.unitravel.infrastructure.adapters.out.database.mappers.documenttype;

import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DocumentTypeMapper {

    DocumentType entityToDomain(DocumentTypeEntity entity);

    DocumentTypeEntity domainToEntity(DocumentType domain);

    void mergeToEntity(@MappingTarget DocumentTypeEntity target, DocumentType source);

    List<DocumentType> entitiesToDomains(List<DocumentTypeEntity> entities);
}
