package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {
}
