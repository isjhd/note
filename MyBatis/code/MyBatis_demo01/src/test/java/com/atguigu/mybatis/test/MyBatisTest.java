package com.atguigu.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class MyBatisTest {
    @Test
    public void testMyBatis() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUser = userMapper.getAllUser();
        allUser.forEach(user -> System.out.println(user));
    }
}
