package cn.itcast.netty.c3;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        //1.创建事件循环组
        EventLoopGroup group1 = new NioEventLoopGroup(2);// io事件，普通任务，定时任务
//        EventLoopGroup group2 = new DefaultEventLoopGroup();// 普通任务，定时任务
        //2.获取下一个事件循环对象
        System.out.println(group1.next());
        System.out.println(group1.next());
        System.out.println(group1.next());
        System.out.println(group1.next());

        //3.执行普通任务
//        group1.next().submit(() -> {
//            try {
//                Thread.sleep(1000);
//                log.debug("ok");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        log.debug("main");

        //4.执行定时任务
        group1.next().scheduleAtFixedRate(() -> {
           log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);
        log.debug("main");

    }
}
