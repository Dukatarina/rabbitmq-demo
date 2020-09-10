package com.xxx.demo.rabbitmqSpring;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitTemplateTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().put("desc", "自定义描述");
        properties.getHeaders().put("type", "自定义类型");
        Message message = new Message("hello rabbitTemplate".getBytes(), properties);
        rabbitTemplate.convertAndSend("test_spring_fanout", "", message, message1 -> {
            message1.getMessageProperties().getHeaders().put("desc", "自定义描述--postProcessMessage");
            return message1;
        });
        rabbitTemplate.convertAndSend("test_spring_fanout", "", message);
    }
}
