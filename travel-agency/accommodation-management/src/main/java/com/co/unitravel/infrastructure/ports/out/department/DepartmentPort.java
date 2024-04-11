package com.co.unitravel.infrastructure.ports.out.department;

import com.co.unitravel.domain.models.Department;

import java.util.List;

public interface DepartmentPort {
     Department save(Department department);

     List<Department> findAll();
}
