package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(C) > 0 THEN TRUE ELSE FALSE END " +
            "FROM CityEntity C WHERE C.name =:name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT C FROM CityEntity C WHERE C.department.id =:departmentId")
    List<CityEntity> findByDepartment(@Param("departmentId") Integer departmentId);
}
