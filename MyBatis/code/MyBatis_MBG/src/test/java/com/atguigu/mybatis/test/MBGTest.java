package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/* @author  i-s-j-h-d
 * @version 1.0 */
public class MBGTest {

    @Test
    public void testMBG() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            //查询所有数据
            /*List<Emp> list = mapper.selectByExample(null);
            list.forEach(emp -> System.out.println(emp));*/

            // 根据条件查询
            /*EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNotNull();
            List<Emp> list = mapper.selectByExample(example);
            list.forEach(emp -> System.out.println(emp));*/

            // 普通添加，会将null所对应的字段修改为null
            mapper.updateByPrimaryKey(new Emp(1,"admin",22,null,"456q@@.com",3));

            // 选择性添加，不会将null所对应的字段修改为null
            // mapper.updateByPrimaryKeySelective(new Emp(1,"admin",22,null,"456q@@.com",3));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
