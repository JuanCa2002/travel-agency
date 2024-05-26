package com.co.unitravel.infrastructure.config;

import com.co.unitravel.infrastructure.config.constants.AmqpConfigurationConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    @Bean
    public Queue authenticationUserQueue() {
        return new Queue(AmqpConfigurationConstants.AUTHENTICATION_QUEUE);
    }

    @Bean
    public Binding authenticationUserBinding(@Qualifier("authenticationUserQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.AUTHENTICATION_ROUTING_KEY);
    }

    @Bean
    public TopicExchange exchangeAuthentication() {
        return new TopicExchange(AmqpConfigurationConstants.EXCHANGE);
    }


    @Bean
    public MessageConverter converterAuthentication() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate templateAuthentication(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converterAuthentication());
        return rabbitTemplate;
    }

}
