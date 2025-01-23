package org.example.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.example.mybatis.mapper.SQLMapper;
import org.example.mybatis.pojo.User;
import org.example.mybatis.utils.SqlSessionUtils;
import org.junit.Test;

import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class SQLMapperTest {

    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByLike("a");
        System.out.println(list);
    }

    @Test
    public void testDeleteMore() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        int result = mapper.deleteMore("1,3");
        System.out.println(result);
    }

    @Test
    public void testGetUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByTableName("t_user");
        System.out.println(list);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "123@162.com");
        mapper.insertUser(user);
        System.out.println(user);
    }

}
