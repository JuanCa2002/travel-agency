package com.co.unitravel.infrastructure.adapters.out.database.mappers.department;

import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {

    Department entityToDomain(DepartmentEntity entity);

    DepartmentEntity domainToEntity(Department department);

    List<Department> domainsToEntities(List<Department> departmentEntityList);

    List<Department> entitiesToDomains(List<DepartmentEntity> departmentEntityList);

}
