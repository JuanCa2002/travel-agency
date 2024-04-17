package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.PermissionEntity;
import com.co.unitravel.infrastructure.adapters.out.database.entities.RolPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(P) > 0 THEN TRUE ELSE FALSE END " +
            "FROM PermissionEntity P WHERE P.id = :id")
    boolean existsById(@Param("id") Long id);

    @Query("SELECT P FROM RolPermissionEntity RP JOIN PermissionEntity P ON " +
            "RP.permission.id = P.id " +
            "WHERE RP.rol.id = :roleId")
    List<PermissionEntity> findPermissionById(@Param("roleId") Long roleId);
}
