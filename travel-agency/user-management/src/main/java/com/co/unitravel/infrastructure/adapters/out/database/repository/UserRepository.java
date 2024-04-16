package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(U) > 0 THEN TRUE ELSE FALSE END " +
            "FROM UserEntity U WHERE U.documentNumber = :documentNumber")
    boolean existsByDocument(@Param("documentNumber") String documentNumber);

}
