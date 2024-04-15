package com.co.unitravel.infrastructure.adapters.out.database.entities

import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_SEAT", sequenceName = "SEQ_SEAT", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "seat")
data class SeatEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEAT")
        @Column(name = "id", length = 10)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "airplane_id", nullable = false)
        var airplane: AirplaneEntity? = AirplaneEntity(),

        @Column(name = "number_seat", nullable = false, length = 4)
        var numberSeat: Int? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "seatClass", nullable = false)
        var seatClass: SeatClass? = null,

        @Column(name = "price", nullable = false, length = 8)
        var price: Int? = null,

        @Column(name = "customer_id", nullable = false, length = 8)
        var customerId: Long? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "seatStatus", nullable = false)
        var seatStatus: SeatStatus? = null
)
