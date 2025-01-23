package com.atguigu.mv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class hello {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/param")
    public String param() {
        return "test_param";
    }

}
