package com.co.unitravel.infrastructure.adapters.out.database.mappers.user;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.out.database.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserEntity domainToEntity(User user);

    User entityToDomain(UserEntity entity);
}
