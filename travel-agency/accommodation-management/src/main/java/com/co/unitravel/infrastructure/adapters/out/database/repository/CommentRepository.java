package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT C FROM CommentEntity C WHERE C.accommodation.id = :accommodationId")
    List<CommentEntity> findByAccommodation(Long accommodationId);

    @Query("SELECT C FROM CommentEntity C WHERE C.customerId = :customerId")
    List<CommentEntity> findByCustomer(Long customerId);
}
