package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        //返回视图名称
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "target";
    }
}
