package com.co.unitravel.infrastructure.adapters.out.database.entities

import com.co.unitravel.domain.models.enums.AirplaneStatus
import jakarta.persistence.*
import lombok.AllArgsConstructor

import lombok.NoArgsConstructor

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_AIRPLANE", sequenceName = "SEQ_AIRPLANE", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "airplane")
data class AirplaneEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AIRPLANE")
    @Column(name = "id", length = 10)
    var id: Long? = null,

    @Column(name = "model", nullable = false, length = 10)
    var model:String? = null,

    @Column(name = "number_seats", nullable = false, length = 4)
    var numberSeats:Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 15)
    var status:AirplaneStatus? = null,

)