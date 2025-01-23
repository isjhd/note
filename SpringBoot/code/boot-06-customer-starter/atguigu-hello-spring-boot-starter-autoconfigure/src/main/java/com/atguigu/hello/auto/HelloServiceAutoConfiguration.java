package com.atguigu.hello.auto;

/* @author  i-s-j-h-d
 * @version 1.0 */

import com.atguigu.hello.bean.HelloProperties;
import com.atguigu.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@ConditionalOnMissingBean(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        return helloService;
    }

}
