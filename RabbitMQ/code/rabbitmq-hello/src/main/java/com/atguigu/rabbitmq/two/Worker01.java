package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

//这时是一个工作线程(相当于之前消费者)
public class Worker01 {

    //队列的名称
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息：" + new String(message.getBody()));
        };

        //消息接收被取消时，执行下面的内容
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消息者取消消费接口回调逻辑");
        };

        //消息的接收
        /**
         * 消费者消息
         * 第一个参数：消费哪个队列
         * 第二个参数：消费成功之后是否要自动应答，true代表的自动应答，false代表手动应答
         * 第三个参数：消费者成功消费的回调
         * 第四个参数：消费者取消消费的回调
         */
        System.out.println("C1等待接收消息.....");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
