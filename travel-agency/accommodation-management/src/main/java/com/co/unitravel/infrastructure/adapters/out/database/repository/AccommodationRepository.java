package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.AccommodationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {

    @Query("SELECT AE FROM AccommodationEntity AE")
    Page<AccommodationEntity> findByCriteria(Pageable pageable);

    @Query("SELECT AE FROM AccommodationEntity AE WHERE AE.destination.id =:destinationId")
    AccommodationEntity findByDestination(@Param("destinationId") Long destinationId);

    @Query("SELECT CASE WHEN COUNT(AE) > 0 THEN TRUE ELSE FALSE END " +
            "FROM AccommodationEntity AE WHERE AE.id = :id")
    boolean existsById(@Param("id") Long id);

    @Query("SELECT AE FROM AccommodationEntity AE WHERE AE.administratorId = :administratorId")
    List<AccommodationEntity> findByAdministratorId(@Param("administratorId") Long administratorId);

}
