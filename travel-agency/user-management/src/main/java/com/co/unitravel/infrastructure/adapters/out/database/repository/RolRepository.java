package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity, Long> {
}
