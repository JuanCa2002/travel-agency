package com.co.unitravel.infrastructure.ports.in.department;

import com.co.unitravel.domain.models.Department;

import java.util.List;

public interface DepartmentUseCase {

    Department create(Department department);

    List<Department> getAll();
}
