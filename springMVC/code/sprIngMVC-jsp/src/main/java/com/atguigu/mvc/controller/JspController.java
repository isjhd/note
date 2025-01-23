package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class JspController{

    @RequestMapping("/succession")
    public String success(){
        return "success";
    }


}
