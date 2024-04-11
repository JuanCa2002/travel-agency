package com.co.unitravel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ReservationManagementApplication

fun main(args: Array<String>) {
    runApplication<ReservationManagementApplication>(*args)
}