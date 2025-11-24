package com.atguigu.rabbitmq.one;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

//生产者：发消息
public class Producer {
    //队列名称
    public static final String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args) throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP连接RabbitMQ的队列
        factory.setHost("192.168.13.129");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");
        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列
         *
         * 第一个参数：队列名称
         * 第二个参数：队列里面的消息是否持久化（磁盘）默认情况消息存储在内存中
         * 第三个参数：该队列是否只供一个消费者进行消费 是否进行消息共享，true可以多个消费者消费，false只能一个消费者消费
         * 第四个参数：是否自动删除最后一个消费者端连接以后该队列是否自动删除，true自动删除，false不自动删除
         * 其它参数
         */
        Map<String, Object> arguments = new HashMap<>();
        //官方允许是0-255之间，此处设置10，允许优化级范围0-10，不要设置过大浪费CPU和内存
        arguments.put("x-max-priority", 10);
        channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
        //发消息
        for(int i = 1; i < 11; i++) {
            String message = "info" + i;
            if(i == 5) {
                AMQP.BasicProperties properties =
                        new AMQP.BasicProperties().builder().priority(5).build();
                channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
            } else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        }

        /**
         * 发送一个消息
         * 第一个参数：发送到哪个交换机
         * 第二个参数：路由的Key值是哪个，队列的名称
         * 第三个参数：其它参数信息
         * 第四个参数：发送消息的消息体
         */
        System.out.println("消息发送完毕");
    }

}
