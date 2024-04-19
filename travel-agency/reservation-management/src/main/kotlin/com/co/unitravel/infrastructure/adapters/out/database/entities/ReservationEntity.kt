package com.co.unitravel.infrastructure.adapters.out.database.entities

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.ReservationPaymentMethod
import com.co.unitravel.domain.models.enums.ReservationStatus
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_RESERVATION", sequenceName = "SEQ_RESERVATION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "reservation")
data class ReservationEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVATION")
    @Column(name = "id", length = 10)
    var id: Long? = null,

    @Column(name = "accommodation_id", nullable = false, length = 8)
    var accommodationId: Long? = null,

    @Column(name = "customer_id", nullable = false, length = 8)
    var customerId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = true)
    var flight: FlightEntity? = FlightEntity(),

    @Column(name = "reservation_date", nullable = false, length = 8)
    var reservationDate: LocalDate? = null,

    @Column(name = "checkIn_date", nullable = false, length = 8)
    var checkInDate: LocalDate? = null,

    @Column(name = "checkOut_date", nullable = false, length = 8)
    var checkOutDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 15)
    var paymentMethod: ReservationPaymentMethod? = null,

    @Column(name = "number_people", nullable = false, length = 8)
    var numberPeople: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status", nullable = false, length = 15)
    var reservationStatus: ReservationStatus? = null,

    @Column(name = "final_price", nullable = false, length = 8)
    var finalPrice: Long? = null
)
