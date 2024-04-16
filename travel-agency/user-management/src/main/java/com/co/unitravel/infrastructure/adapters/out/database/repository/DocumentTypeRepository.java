package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(DT) > 0 THEN TRUE ELSE FALSE END " +
            "FROM DocumentTypeEntity DT WHERE DT.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(DT) > 0 THEN TRUE ELSE FALSE END " +
            "FROM DocumentTypeEntity DT WHERE DT.key = :key")
    boolean existsByKey(@Param("key") String key);
}
