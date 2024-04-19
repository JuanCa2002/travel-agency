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
public class AmqpConfiguration {

    @Bean
    public Queue cityUserQueue() {
        return new Queue(AmqpConfigurationConstants.CITY_QUEUE);
    }

    @Bean
    public Binding cityUserBinding(@Qualifier("cityUserQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.CITY_ROUTING_KEY);
    }

    @Bean
    public Queue userUserQueue() {
        return new Queue(AmqpConfigurationConstants.USER_QUEUE);
    }

    @Bean
    public Binding userUserBinding(@Qualifier("userUserQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfigurationConstants.USER_ROUTING_KEY);
    }

    @Bean
    public TopicExchange exchangeUser() {
        return new TopicExchange(AmqpConfigurationConstants.EXCHANGE);
    }


    @Bean
    public MessageConverter converterUser() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate templateUser(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converterUser());
        return rabbitTemplate;
    }
}
