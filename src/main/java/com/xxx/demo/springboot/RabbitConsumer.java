package com.xxx.demo.springboot;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitConsumer {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "springboot_queue", durable = "true"),
                    exchange = @Exchange(value = "exchange_springboot", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
                    key = "springboot.#")
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        System.out.println(message.getPayload());
        Long deliveryTay = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ack
        channel.basicAck(deliveryTay, false);
    }
}
























