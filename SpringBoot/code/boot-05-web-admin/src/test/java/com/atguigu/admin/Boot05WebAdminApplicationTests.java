package com.atguigu.admin;


import ch.qos.logback.classic.Logger;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {

        Long aLong = jdbcTemplate.queryForObject("select count(*) from student", Long.class);

        log.info("记录总数：{}",aLong);

        log.info("数据源类型：{}",dataSource.getClass());

    }

    @Test
    void testUserMapper() {
        User user = userMapper.selectById(1L);
        log.info("用户信息：{}", user);
    }

//    @Test
//    void testRedis() {
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        operations.set("hello","world");
//        String hello = operations.get("hello");
//        System.out.println(hello);
//        System.out.println(redisConnectionFactory.getClass());
//    }


}
