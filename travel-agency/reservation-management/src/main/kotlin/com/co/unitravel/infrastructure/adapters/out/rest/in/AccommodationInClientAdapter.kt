package com.co.unitravel.infrastructure.adapters.out.rest.`in`

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants
import com.co.unitravel.infrastructure.ports.out.client.`in`.AccommodationInClientPort
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class AccommodationInClientAdapter(private val rabbitTemplate: RabbitTemplate)
    :AccommodationInClientPort{
    override fun findById(id: Long): Boolean {
        val objectMapper = ObjectMapper()
        val idJ = objectMapper.writeValueAsString(id)
        val response:Any? = rabbitTemplate.convertSendAndReceive(
            AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.ACCOMMODATION_ROUTING_KEY, idJ)
        if(response==null) return false
        val res: Boolean = String(response as ByteArray).toBoolean()
        return res;
    }

    override fun findPrice(id: Long): Long {
        val objectMapper = ObjectMapper()
        val idJ = objectMapper.writeValueAsString(id)
        val response:Any? = rabbitTemplate.convertSendAndReceive(
            AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.ACCOMMODATION_PRICE_ROUTING_KEY, idJ)
        if(response==null) return 0L
        val res: Long = String(response as ByteArray).toLong()
        return res;
    }

    override fun findMaximumCapacity(id: Long): Int {
        val objectMapper = ObjectMapper()
        val idJ = objectMapper.writeValueAsString(id)
        println("El id enviado$idJ")
        val response:Any? = rabbitTemplate.convertSendAndReceive(
            AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.ACCOMMODATION_CAPACITY_ROUTING_KEY, idJ)
        if(response==null) return 0
        val res: Int = String(response as ByteArray).toInt()
        println("Maxima capacidad$res")
        return res;
    }
}