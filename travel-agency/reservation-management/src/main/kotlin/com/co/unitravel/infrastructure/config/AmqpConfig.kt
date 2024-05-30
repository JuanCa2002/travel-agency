package com.co.unitravel.infrastructure.config

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean

open class AmqpConfig {

    @Bean
    open fun cityReservationQueue(): Queue {
        return Queue(AmqpConfigurationConstants.CITY_QUEUE)
    }

    @Bean
    open fun cityReservationBinding(@Qualifier("cityReservationQueue") queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.CITY_ROUTING_KEY)
    }

    @Bean
    open fun userReservationQueue(): Queue {
        return Queue(AmqpConfigurationConstants.USER_QUEUE)
    }

    @Bean
    open fun userReservationBinding(@Qualifier("userReservationQueue") queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.USER_ROUTING_KEY)
    }

    @Bean
    fun accommodationReservationQueue(): Queue {
        return Queue(AmqpConfigurationConstants.ACCOMMODATION_QUEUE)
    }

    @Bean
    fun accommodationReservationBinding(@Qualifier("accommodationReservationQueue") queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.ACCOMMODATION_ROUTING_KEY)
    }

    @Bean
    fun accommodationReservationQueuePrice(): Queue {
        return Queue(AmqpConfigurationConstants.ACCOMMODATION_QUEUE_PRICE)
    }

    @Bean
    fun accommodationReservationPriceBinding(@Qualifier("accommodationReservationQueuePrice") queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.ACCOMMODATION_PRICE_ROUTING_KEY)
    }

    @Bean
    fun accommodationReservationQueueCapacity(): Queue {
        return Queue(AmqpConfigurationConstants.ACCOMMODATION_QUEUE_CAPACITY)
    }

    @Bean
    fun accommodationReservationCapacityBinding(@Qualifier("accommodationReservationQueueCapacity") queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.ACCOMMODATION_CAPACITY_ROUTING_KEY)
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