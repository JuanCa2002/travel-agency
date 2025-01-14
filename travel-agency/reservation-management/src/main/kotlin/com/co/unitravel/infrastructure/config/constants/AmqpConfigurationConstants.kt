package com.co.unitravel.infrastructure.config.constants

object AmqpConfigurationConstants {

    const val CITY_QUEUE: String = "city_queue"

    const val CITY_ROUTING_KEY: String = "city_routingKey"

    const val USER_QUEUE: String = "user_queue"

    const val USER_ROUTING_KEY: String = "user_routingKey"

    const val ACCOMMODATION_QUEUE: String = "accommodation_queue"

    const val ACCOMMODATION_ROUTING_KEY: String = "accommodation_routingKey"

    const val ACCOMMODATION_QUEUE_PRICE: String = "accommodation_queue_price"

    const val ACCOMMODATION_PRICE_ROUTING_KEY: String = "accommodation_price_routingKey"

    const val ACCOMMODATION_QUEUE_CAPACITY: String = "accommodation_queue_capacity"

    const val ACCOMMODATION_CAPACITY_ROUTING_KEY: String = "accommodation_capacity_routingKey"

    const val EXCHANGE: String = "rabbit_exchange"
}