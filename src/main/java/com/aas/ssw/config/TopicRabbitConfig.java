package com.aas.ssw.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "rabbitmq.enabled")
public class TopicRabbitConfig {
    public final static String MESSAGE1 = "topic.message1";
    public final static String MESSAGE2 = "topic.messages2";
    public final static String EXANGE = "exange";

    /**
     * 第一个queue，名字叫topic.message1
     * @return
     */
    @Bean
    public Queue queueMessage1() {
        return new Queue(MESSAGE1);
    }

    /**
     * 第二个queue，名字叫topic.message2
     * @return
     */
    @Bean
    public Queue queueMessage2() {
        return new Queue(MESSAGE2);
    }

    /**
     * topicexchange，名字叫exange
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXANGE);
    }

    /**
     * 绑定queue1到topicexchange,并指定binding key为topic.message1
     * @param queueMessage1
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage1(Queue queueMessage1, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage1).to(exchange).with(MESSAGE1);
    }

    /**
     * 绑定queue2到topicexchange,并指定binding key为topic.#
     * @param queueMessage2
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage2(Queue queueMessage2, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage2).to(exchange).with("topic.#");
    }
}
