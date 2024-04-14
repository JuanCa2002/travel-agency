package com.co.unitravel.infrastructure.config

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean

open class AmqpConfig {

    @Bean
    open fun cityQueue(): Queue {
        return Queue(AmqpConfigurationConstants.CITY_QUEUE)
    }

    @Bean
    open fun cityBinding(queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.CITY_ROUTING_KEY)
    }

    @Bean
    open fun exchange(): TopicExchange {
        return TopicExchange(AmqpConfigurationConstants.EXCHANGE)
    }


    @Bean
    open fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }


    @Bean
    open fun template(connectionFactory: ConnectionFactory?): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converter()
        return rabbitTemplate
    }
}