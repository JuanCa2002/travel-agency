package com.co.unitravel.infrastructure.adapters.out.rest.`in`

import com.co.unitravel.domain.models.client.City
import com.co.unitravel.infrastructure.adapters.out.rest.`in`.mappers.CityMapperClient
import com.co.unitravel.infrastructure.adapters.out.rest.`in`.responses.CityResponse
import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants
import com.co.unitravel.infrastructure.ports.out.client.`in`.CityInClientPort
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import lombok.RequiredArgsConstructor
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import com.google.gson.JsonElement
import com.google.gson.JsonParser

@Component
@RequiredArgsConstructor
class CityInClientAdapter(private val rabbitTemplate: RabbitTemplate,
                          private val cityMapperClient: CityMapperClient)
    : CityInClientPort{

    override fun findById(id: Long):Boolean {
        val objectMapper = ObjectMapper()
        val idJ = objectMapper.writeValueAsString(id)
        println("El id enviado$idJ")
        val response:Any? = rabbitTemplate.convertSendAndReceive(
            AmqpConfigurationConstants.EXCHANGE, AmqpConfigurationConstants.CITY_ROUTING_KEY, idJ)
        if(response==null) return false
        val res: Boolean = String(response as ByteArray).toBoolean()
        println(res)
        return res;
    }
//    override fun findById(id: Long): City {
//        val objectMapper = ObjectMapper()
//        val idJ = objectMapper.writeValueAsString(id)
//        println("El id enviado$idJ")
//        val response: Any? = rabbitTemplate.convertSendAndReceive(
//            AmqpConfigurationConstants.EXCHANGE,
//            AmqpConfigurationConstants.CITY_ROUTING_KEY,
//            idJ)
//        if(response == null) println("City no encontrada")
//        println("tu respuesta papu$response")
//         val gson = Gson()
//        val json = String(response as ByteArray, Charsets.UTF_8)
//        val jsonResponse = String(response, Charsets.UTF_8)
//         println("Tu respuesta: $jsonResponse")
//
//        val jsonElement: JsonElement = JsonParser.parseString(json)
//        println(jsonElement)
//        //val respuesta: CityResponse = gson.fromJson(jsonElement, CityResponse::class.java)
//        val respuesta = objectMapper.readValue(json, CityResponse::class.java)
//        return cityMapperClient.responseToDomain(respuesta);
//    }


}