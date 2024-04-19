package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(R) > 0 THEN TRUE ELSE FALSE END " +
            "FROM RolEntity R WHERE R.name = UPPER(:name)")
    boolean existsByName(@Param("name") String name);
}
