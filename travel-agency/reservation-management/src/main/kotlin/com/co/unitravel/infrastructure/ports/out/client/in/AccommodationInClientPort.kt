package com.co.unitravel.infrastructure.ports.out.client.`in`

interface AccommodationInClientPort {
    fun findById(id:Long):Boolean

    fun findPrice(id: Long): Long

    fun findMaximumCapacity(id: Long): Int
}