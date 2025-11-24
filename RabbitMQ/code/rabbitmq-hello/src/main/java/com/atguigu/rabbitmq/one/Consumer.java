package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.*;

//消费者：接收消息
public class Consumer {
    //队列的名称
    public static final String QUEUE_NAME = "hello";
    //接收消息
    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.13.129");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //声明接受消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };

        //取消消息的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };

        /**
         * 消费者消息
         * 第一个参数：消费哪个队列
         * 第二个参数：消费成功之后是否要自动应答，true代表的自动应答，false代表手动应答
         * 第三个参数：消费者成功消费的回调
         * 第四个参数：消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
