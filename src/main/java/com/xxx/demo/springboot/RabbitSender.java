package com.xxx.demo.springboot;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        System.out.println("correlationData:" + correlationData);
        System.out.println("ack:" + ack);
        System.out.println("cause:" + cause);
        if (!ack) {
            System.out.println("异常处理-cause: " + cause);
        }
    };
    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        System.err.println(String.valueOf(message) + replyCode + replyText + exchange + routingKey);
    };


    public void send(Object message, Map<String, Object> properties) {
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConfirmCallback(confirmCallback);

        MessageHeaders mhs = new MessageHeaders(properties);
        Message<Object> msg = MessageBuilder.createMessage(message, mhs);
        CorrelationData cd = new CorrelationData("1234567890-" + System.currentTimeMillis());

        rabbitTemplate.convertAndSend("exchange_springboot", "springboot.hello", msg, cd);
    }
}

















