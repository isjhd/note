package com.atguigu.hello.service;

/* @author  i-s-j-h-d
 * @version 1.0 */

import com.atguigu.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认不要放在容器中
 */
public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String userName) {
        return helloProperties.getPrefix() + "：" + userName + "》" + helloProperties.getSuffix();
    }

}
