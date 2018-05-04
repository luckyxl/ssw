package com.aas.ssw;

import com.aas.ssw.business.example.entity.User;
import com.aas.ssw.business.example.rabbitmq.RabbitMqSender;
import com.aas.ssw.config.TopicRabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RabbitMqTest {


    @Resource
    private RabbitMqSender rabbitMqSender;

    @Test
    public void mqTest(){
        User user = new User();
        user.setLoginName("zhangsan");
        user.setRealName("张三");
        rabbitMqSender.send(user,TopicRabbitConfig.MESSAGE1);
        rabbitMqSender.send(user,TopicRabbitConfig.MESSAGE2);
    }

}
