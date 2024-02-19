package com.epam.upskill.workload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @date: 19 February 2024 $
 * @time: 2:39 AM 09 $
 * @author: Qudratjon Komilov
 */
@Configuration
public class ConfigMQ {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.workload}")
    private String workloadQueue;

    @Value("${rabbitmq.routing-keys.internal-workload}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue workloadQueue() {
        return new Queue(this.workloadQueue);
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(workloadQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }

}
