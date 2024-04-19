package com.co.unitravel.infrastructure.adapters.out.database.repository;

import com.co.unitravel.infrastructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(U) > 0 THEN TRUE ELSE FALSE END " +
            "FROM UserEntity U WHERE U.documentNumber = :documentNumber")
    boolean existsByDocument(@Param("documentNumber") String documentNumber);

    @Query("SELECT CASE WHEN COUNT(U) > 0 THEN TRUE ELSE FALSE END " +
            "FROM UserEntity U WHERE U.id = :id")
    boolean existsById(@Param("id") Long id);


    @Query("SELECT U FROM UserEntity U WHERE U.documentNumber = :documentNumber " +
            "AND U.documentType.id = :documentTypeId")
    UserEntity findByIdentification(@Param("documentNumber") String documentNumber,
                                    @Param("documentTypeId") Integer documentTypeId);

    @Query("SELECT U FROM UserEntity U WHERE U.email = :email")
    UserEntity findByEmail(@Param("email") String email);

}
