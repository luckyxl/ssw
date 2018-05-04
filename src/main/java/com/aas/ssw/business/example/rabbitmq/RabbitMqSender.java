package com.aas.ssw.business.example.rabbitmq;

import com.aas.ssw.business.example.entity.User;
import com.aas.ssw.config.TopicRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMqSender {

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 向rabbitmq中发送数据
     * @param user
     * @param routingKey 路由键
     */
    public void send(User user,String routingKey) {
        amqpTemplate.convertAndSend(TopicRabbitConfig.EXANGE, routingKey, user);
    }
}
