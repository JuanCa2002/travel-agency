package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.domain.models.enums.ReservationStatus
import com.co.unitravel.infrastructure.adapters.out.database.entities.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface ReservationRespository: JpaRepository<ReservationEntity, Long> {

    @Query("SELECT R FROM ReservationEntity R WHERE (:accommodationId IS NULL OR R.accommodationId = :accommodationId)" +
            "AND (:customerId IS NULL OR R.customerId = :customerId) " +
            "AND (:checkInDate IS NULL OR R.checkInDate = :checkInDate) " +
            "AND (:reservationStatus IS NULL OR R.reservationStatus = :reservationStatus) ")
    fun findAllByCriteria(@Param("accommodationId") accommodationId: Long?,
                          @Param("customerId") customerId: Long?,
                          @Param("checkInDate") checkInDate: LocalDate?,
                          @Param("reservationStatus") reservationStatus: ReservationStatus?
    ): List<ReservationEntity>

    @Query("SELECT CASE WHEN COUNT(R) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ReservationEntity R WHERE (:checkInDate BETWEEN R.checkInDate and R.checkOutDate) OR " +
            "(:checkOutDate BETWEEN R.checkInDate and R.checkOutDate)")
    fun validateDates(@Param("checkInDate") checkInDate: LocalDate,
                      @Param("checkOutDate") checkOutDate: LocalDate): Boolean
}