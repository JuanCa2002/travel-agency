package com.co.unitravel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@SpringBootApplication
open class ReservationManagementApplication{
    @Bean
    open fun validator(): LocalValidatorFactoryBean {
        return LocalValidatorFactoryBean()
    }
}

fun main(args: Array<String>) {
    runApplication<ReservationManagementApplication>(*args)
}