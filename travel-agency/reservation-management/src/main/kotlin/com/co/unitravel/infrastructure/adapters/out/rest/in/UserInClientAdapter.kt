package com.co.unitravel.infrastructure.adapters.out.rest.`in`

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants
import com.co.unitravel.infrastructure.ports.out.client.`in`.UserInClientPort
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class UserInClientAdapter(private val rabbitTemplate: RabbitTemplate)
    :UserInClientPort{
    override fun findById(id: Long): Boolean {
        val objectMapper = ObjectMapper()
        val idJ = objectMapper.writeValueAsString(id)
        println("El id enviado$idJ")
        val response:Any? = rabbitTemplate.convertSendAndReceive(
            AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.USER_ROUTING_KEY, idJ)
        if(response==null) return false
        val res: Boolean = String(response as ByteArray).toBoolean()
        println(res)
        return res;
    }
}