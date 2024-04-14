package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    @Query("SELECT DE FROM DestinationEntity DE WHERE DE.city.id =:cityId")
    DestinationEntity findByCity(@Param("cityId") Long cityId);

    @Query("SELECT CASE WHEN COUNT(DE) > 0 THEN TRUE ELSE FALSE END " +
            "FROM DestinationEntity DE WHERE DE.city.id =:cityId")
    boolean existsByCity(@Param("cityId") Long cityId);
}
