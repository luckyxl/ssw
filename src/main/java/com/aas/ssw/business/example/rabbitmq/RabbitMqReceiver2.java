package com.aas.ssw.business.example.rabbitmq;

import com.aas.ssw.business.example.entity.User;
import com.aas.ssw.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * rabbitmq消息接收者，接收队列名为topic.message2
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.MESSAGE2)
public class RabbitMqReceiver2 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("RabbitMqReceiver2:" + user.getLoginName());
        System.out.println("RabbitMqReceiver2:" + user.getRealName());
    }
}
