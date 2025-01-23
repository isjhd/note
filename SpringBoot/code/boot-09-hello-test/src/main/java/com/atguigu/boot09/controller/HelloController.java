package com.atguigu.boot09.controller;

import com.atguigu.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author  i-s-j-h-d
 * @version 1.0 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping
    public String sayHello() {
        String s = helloService.sayHello("张三");
        return s;
    }


}
