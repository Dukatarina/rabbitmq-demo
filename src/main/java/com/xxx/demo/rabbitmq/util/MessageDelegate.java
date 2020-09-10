package com.xxx.demo.rabbitmq.util;

public class MessageDelegate {
    public void handleMessage(byte[] messageBody) {
        System.out.println("handleMessage" + new String(messageBody));
    }

    public void myHandleMessage(String messageBody) {
        System.out.println("myHandleMessage" + messageBody);
    }
}
