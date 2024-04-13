package com.co.unitravel.infrastructure.adapters.out.database.implementation;


import com.co.unitravel.application.exceptions.department.DepartmentErrorCodes;
import com.co.unitravel.application.exceptions.department.DepartmentNotFoundException;
import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DepartmentEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.department.DepartmentMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.DepartmentRepository;
import com.co.unitravel.infrastructure.ports.out.department.DepartmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentAdapter implements DepartmentPort {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public Department save(Department department) {
        DepartmentEntity savedDepartment = departmentRepository.save(departmentMapper.domainToEntity(department));
        return departmentMapper.entityToDomain(savedDepartment);
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.entitiesToDomains(departmentRepository.findAll());
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }

    @Override
    public Department findById(Integer id) throws DepartmentNotFoundException {
        DepartmentNotFoundException errorNotFound = new DepartmentNotFoundException();
        errorNotFound.addError(DepartmentErrorCodes.DEPARTMENT_NOT_FOUND,new Object[]{id});
        return departmentMapper.entityToDomain(departmentRepository.findById(id).orElseThrow(() -> errorNotFound));
    }
}
