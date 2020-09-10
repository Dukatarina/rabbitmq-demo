package com.xxx.demo.rabbitmqApi.quickStart11dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xxx.demo.rabbitmqApi.Channels;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.stream.IntStream;

@SpringBootTest
public class Producer {
    @Autowired
    private Channels channels;

    @Test
    public void doTest() {
        Channel channel = channels.getChannelList().get(0);
        String exchangeName = "test_dlx_exchange";
        String routingKey = "test_dlx";
        String msg = "hello world dlx";
        IntStream.range(0, 5).forEach(i -> {
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).contentEncoding("utf-8").expiration("1000").build();

            try {
                channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
