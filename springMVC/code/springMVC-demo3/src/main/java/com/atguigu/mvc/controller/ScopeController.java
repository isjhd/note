package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class ScopeController {

    @RequestMapping("/testRequestByServletAPI")
    public String testServletAPI(HttpServletRequest request){
        request.setAttribute("testScope", "hello,servletAPI");
        return "success";
    }

    @RequestMapping("/testModelandView")
    public ModelAndView testModelAndView() {
        /**
         * ModelAndView有Model和View的功能
         * Model主要用于向请求域共享数据
         * View主要用于设置视图，实现页面跳转
         */
        ModelAndView mav = new ModelAndView();
        // 向请求域共享数据
        mav.addObject("testScope", "hello, modelAndView");
        //设置视图，实现页面跳转
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testScope", "hell,Model");
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("testScope", "hello, Map");
        return "success";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testScope", "hello, ModelMap");
        return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope", "hello, session");
        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope", "hello, application");
        return "success";
    }

}
