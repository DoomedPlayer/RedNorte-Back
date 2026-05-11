package com.RedNorte.SaludRedNorte.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_REASIGNACION = "cola.reasignacion.pacientes";
    public static final String EXCHANGE_REASIGNACION = "exchange.reasignacion";
    public static final String ROUTING_KEY = "reasignacion.key";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_REASIGNACION, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_REASIGNACION);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
