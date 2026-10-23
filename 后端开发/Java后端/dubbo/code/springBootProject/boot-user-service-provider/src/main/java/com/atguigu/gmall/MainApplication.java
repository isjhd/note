package com.atguigu.gmall;



import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;


/**
 * 1.导入依赖
 *      1.1 导入dubbo-starter
 *      1.2 导入dubbo的其他依赖
 */
@EnableDubbo//开启基于注解的dubbo功能
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MainApplication.class, args);
    }

}
