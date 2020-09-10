package com.xxx.demo.rabbitmqSpringboot;

import com.xxx.demo.springboot.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RabbitSenderTest {
    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void doTest() {
        Map<String, Object> map = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        map.put("number", 123456);
        map.put("send_time", localDateTime);

        rabbitSender.send("hello springboot rabbitmq !", map);
    }
}
























