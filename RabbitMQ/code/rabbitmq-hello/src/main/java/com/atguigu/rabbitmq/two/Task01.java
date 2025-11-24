package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

//生产者，发送大量消息
public class Task01 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    //发送大量消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        /**
         * 生成一个队列
         *
         * 第一个参数：队列名称
         * 第二个参数：队列里面的消息是否持久化（磁盘）默认情况消息存储在内存中
         * 第三个参数：该队列是否只供一个消费者进行消费 是否进行消息共享，true可以多个消费者消费，false只能一个消费者消费
         * 第四个参数：最后一个消费者端连接以后该队列是否自动删除，true自动删除，false不自动删除
         * 其它参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //从控制台当中接收信息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String message = scanner.next();
            /**
             * 发送一个消息
             * 第一个参数：发送到哪个交换机
             * 第二个参数：路由的Key值是哪个，队列的名称
             * 第三个参数：其它参数信息
             * 第四个参数：发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("发送消息完成：" + message);
        }
    }

}
