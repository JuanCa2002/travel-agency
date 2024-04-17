package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.RolPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolPermissionRepository extends JpaRepository<RolPermissionEntity, Long> {
}
