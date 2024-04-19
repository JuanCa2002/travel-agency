package com.co.unitravel.infrastructure.config;

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public Queue cityAccommodationQueue() {
        return new Queue(AmqpConfigurationConstants.CITY_QUEUE);
    }

    @Bean
    public Binding cityAccommodationBinding(@Qualifier("cityAccommodationQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.CITY_ROUTING_KEY);
    }

    @Bean
    public Queue userAccommodationQueue() {
        return new Queue(AmqpConfigurationConstants.USER_QUEUE);
    }

    @Bean
    public Binding userAccommodationBinding(@Qualifier("userAccommodationQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.USER_ROUTING_KEY);
    }

    @Bean
    public Queue accommodationAccommodationQueue() {
        return new Queue(AmqpConfigurationConstants.ACCOMMODATION_QUEUE);
    }

    @Bean
    public Binding accommodationAccommodationBinding(@Qualifier("accommodationAccommodationQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.ACCOMMODATION_ROUTING_KEY);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(AmqpConfigurationConstants.EXCHANGE);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
