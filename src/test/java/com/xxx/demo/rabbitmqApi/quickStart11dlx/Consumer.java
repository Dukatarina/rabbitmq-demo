package com.xxx.demo.rabbitmqApi.quickStart11dlx;

import com.rabbitmq.client.Channel;
import com.xxx.demo.rabbitmqApi.Channels;
import com.xxx.demo.rabbitmqApi.MyConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Consumer {
    @Autowired
    private Channels channels;

    @Test
    public void doTest() throws IOException, InterruptedException {
        Channel channel = channels.getChannelList().get(1);

        String exchangeName = "test_dlx_exchange";
        String routingKey = "test_dlx";
        String queueName = "test_dlx_queue";
        String exchangeType = "direct";

        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "dlx-exchange");

        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName, false, false, false, map);
        channel.queueBind(queueName, exchangeName, routingKey);


        channel.exchangeDeclare("dlx-exchange", "topic", true, false, false, null);
        channel.queueDeclare("dlx-queue", true, false, false, null);
        channel.queueBind("dlx-queue", "dlx-exchange", "#");

        channel.basicQos(0, 1, false);
        //关闭自动签收机制
        channel.basicConsume(queueName, false, new MyConsumer(channel));
        channel.basicConsume("dlx-queue", false, new MyConsumer(channel));
    }
}


















