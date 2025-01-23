package com.atguigu.mv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
//@RequestMapping("/test")
public class TestController {

    //此时请求映射所映射的请求的请求路径为：/test/testRequestMapping
    @RequestMapping(
            value = {"/testRequestMapping",},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String index() {
        return "testRequestMapping";
    }

    @RequestMapping(
            value = {"/test"},
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username", "password=123456"},
            headers = {"Host=localhost:8080"}
    )
    public String testRequestMapping() {
        return "testRequestMapping";
    }

    // @RequestMapping("/a?a/testAnt")
    // @RequestMapping("/a*a/testAnt")
    @RequestMapping("/**/testAnt")
    public String TestAnt() {
        return "testRequestMapping";
    }

    @RequestMapping("/testRest/{id}/{username}")
    public String testRest(@PathVariable("id") String id, @PathVariable("username") String username) {
        System.out.println();
        return "testRequestMapping";
    }


}
