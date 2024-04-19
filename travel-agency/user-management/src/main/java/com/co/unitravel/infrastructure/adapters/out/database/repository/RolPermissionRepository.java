package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.RolPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolPermissionRepository extends JpaRepository<RolPermissionEntity, Long> {

    @Query("SELECT RP FROM RolPermissionEntity RP WHERE RP.rol.id = :rolId")
    List<RolPermissionEntity> findByRole(@Param("rolId") Long rolId);
}
