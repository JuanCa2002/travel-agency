package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(D) > 0 THEN TRUE ELSE FALSE END " +
            "FROM DepartmentEntity D WHERE D.name =:name")
    boolean existsByName(@Param("name") String name);

}
