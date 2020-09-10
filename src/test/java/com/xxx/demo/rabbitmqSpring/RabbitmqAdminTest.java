package com.xxx.demo.rabbitmqSpring;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;

import java.util.HashMap;

@SpringBootTest
public class RabbitmqAdminTest {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    void contextLoads() {
        rabbitAdmin.declareExchange(new DirectExchange("test_spring_direct", false, false));
        rabbitAdmin.declareQueue(new Queue("test_spring_direct_queue", false));
        rabbitAdmin.declareBinding(new Binding("test_spring_direct_queue", Binding.DestinationType.QUEUE, "test_spring_direct", "spring", new HashMap<>()));

        rabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue("test_spring_topic_queue", false))
                        .to(new TopicExchange("test_spring_topic", false, false))
                        .with("user.#")
        );

        rabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue("test_spring_fanout", false))
                        .to(new FanoutExchange("test_spring_fanout_queue", false, false))
        );
    }
}
