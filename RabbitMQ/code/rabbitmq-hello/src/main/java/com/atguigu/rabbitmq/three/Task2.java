package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

//消息在手动应答时是不丢失，放回队列中重新消费
public class Task2 {

    //队列名称
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //声明队列
        boolean durable = true;//需要让Queue进行持久化
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        //从控制台中输入信息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String message = scanner.next();
            //设置生产者发送消息为持久化消息(要求保存到磁盘上)保存在内存中
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN
                    , message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }

}
