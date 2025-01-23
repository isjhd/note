package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.service.AccountService;
import com.atguigu.admin.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/* @author  i-s-j-h-d
 * @version 1.0 */

@Controller
public class indexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CityService cityService;

//    @Autowired
//    StringRedisTemplate redisTemplate;

    @PostMapping("/city")
    public City saveCity(City city) {
        cityService.saveCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/city")
    public City getById(@RequestParam("id") Long id) {
        return cityService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/acct")
    public Account getById(@RequestParam("id") Integer id) {
        return accountService.getAcctByid(id);
    }

//    @ResponseBody
//    @GetMapping("/sql")
//    public String queryFromDb(){
//        Long aLong = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
//        return aLong.toString();
//    }


    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/"})
    public String loginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        if(user.getUserName().equals("wj") && user.getPassword().equals("123")) {
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser", user);
            //登陆成功重定向到main.html，重定向防止表单重复提交
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg", "账号密码错误");
            // 回到登陆页面
            return "login";
        }
    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(Model model) {

//        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);

        return "main";
    }

}
