package com.co.unitravel.infrastructure.adapters.out.database.mappers.comment;

import com.co.unitravel.domain.models.Comment;
import com.co.unitravel.infrastructure.adapters.out.database.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment entityToDomain(CommentEntity entity);

    CommentEntity domainToEntity(Comment domain);

    void mergeToEntity(@MappingTarget CommentEntity target, Comment source);

    List<Comment> entitiesToDomains(List<CommentEntity> entityList);
}
