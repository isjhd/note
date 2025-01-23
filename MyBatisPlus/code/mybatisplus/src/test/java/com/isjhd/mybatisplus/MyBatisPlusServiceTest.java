package com.isjhd.mybatisplus;

import com.isjhd.mybatisplus.pojo.User;
import com.isjhd.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testInsertMore() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("wj" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }

}
