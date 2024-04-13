package com.co.unitravel.application.usecases.department;

import com.co.unitravel.application.exceptions.department.DepartmentBusinessException;
import com.co.unitravel.application.exceptions.department.DepartmentErrorCodes;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.ports.in.department.DepartmentUseCase;
import com.co.unitravel.infrastructure.ports.out.department.DepartmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentUseCaseImpl implements DepartmentUseCase {

    private final DepartmentPort departmentPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Department create(Department department) throws BusinessException {
        DepartmentBusinessException businessError = new DepartmentBusinessException();
        businessError.addError(DepartmentErrorCodes.DEPARTMENT_NAME_ALREADY_EXISTS, null);
        if(departmentPort.existsByName(department.getName().toUpperCase())) throw businessError;
        department.setId(null);
        department.setName(department.getName().toUpperCase());
        return departmentPort.save(department);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Department> getAll() {
        return departmentPort.findAll();
    }
}
