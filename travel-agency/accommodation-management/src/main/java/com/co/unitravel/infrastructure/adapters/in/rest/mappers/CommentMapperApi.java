package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Comment;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperApi {

    @Mapping(target = "accommodation.id", source = "accommodationId")
    @Mapping(target = "comment.id", source = "commentId")
    Comment requestToDomain(CommentRequest request);

    CommentResponse domainToResponse(Comment comment);

    Comment updateRequestToDomain(CommentUpdateRequest commentUpdateRequest);

    List<CommentResponse> domainsToResponses(List<Comment> domains);
}
