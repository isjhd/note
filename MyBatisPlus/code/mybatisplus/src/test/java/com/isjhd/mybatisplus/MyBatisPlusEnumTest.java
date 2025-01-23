package com.isjhd.mybatisplus;

import com.isjhd.mybatisplus.enums.SexEnum;
import com.isjhd.mybatisplus.mapper.UserMapper;
import com.isjhd.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/* @author  i-s-j-h-d
 * @version 1.0 */
@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
    }

}
