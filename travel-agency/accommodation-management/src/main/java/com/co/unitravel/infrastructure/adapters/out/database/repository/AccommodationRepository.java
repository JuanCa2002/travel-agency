package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.AccommodationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {

    @Query("SELECT AE FROM AccommodationEntity AE")
    Page<AccommodationEntity> findByCriteria(Pageable pageable);

    @Query("SELECT AE FROM AccommodationEntity AE WHERE AE.destination.id =:destinationId")
    AccommodationEntity findByDestination(@Param("destinationId") Long destinationId);
}
