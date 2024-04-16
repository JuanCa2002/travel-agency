package com.co.unitravel.infrastructure.adapters.out.database.entities

import com.co.unitravel.domain.models.enums.FlightStatus
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.time.LocalTime

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_FLIGHT", sequenceName = "SEQ_FLIGHT", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "flight")
data class FlightEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLIGHT")
    @Column(name = "id", length = 10)
    var id: Long? = null,

    @Column(name = "initial_city_id", nullable = false, length = 8)
    var initialCityId: Long? = null,

    @Column(name = "final_city_id", nullable = false, length = 8)
    var finalCityId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airplane_id", nullable = false)
    var airplane: AirplaneEntity? = AirplaneEntity(),

    @Column(name = "flight_time", nullable = false, length = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    var flightTime: LocalTime? = null,

    @Column(name = "departure_time", nullable = false, length = 20)
    var departureTime: LocalDate? = null,

    @Column(name = "estimated_arrival_time", nullable = false, length = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    var estimatedArrivalTime: LocalTime? = null,

    @Column(name = "name", nullable = false, length = 20)
    var name: String? = null,

    @Column(name = "price", nullable = false, length = 8)
    var price: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status", nullable = false, length = 15)
    var flightStatus: FlightStatus? = null
)
