package com.atguigu.admin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.*;

/* @author  i-s-j-h-d
 * @version 1.0 */
@SpringBootTest
@DisplayName("junit5功能测试类")
public class Junit5Test {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("测试disPlayName注解")
    @Test
    void testDisplayName() {
        System.out.println(1);
        System.out.println(jdbcTemplate);
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
    void test2() {
        System.out.println(2);
    }

    @RepeatedTest(5)
    @Test
    void test3() {
        System.out.println(5);
    }

    /**
     * 规定方法超时时间，超出时间测试出异常
     * @throws InterruptedException
     */
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试就要开始了...");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试结束了...");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试就要开始了...");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试结束了...");
    }

    /**
     * 断言：前面断言失败，后面的代码都不会执行
     */
    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions() {
        int cal = cal(2,3);
        //相等
        assertEquals(5,cal,"业务逻辑计算失败");

        Object obj1 = new Object();
        Object obj2 = new Object();
        assertSame(obj1,obj2,"两个对象不一样");
    }

    int cal(int i, int j) {
        return i+j;
    }

    @Test
    @DisplayName("array assertion")
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[] {1, 2});
    }

    @Test
    @DisplayName("组合断言")
    void all() {
        assertAll("test",
                ()-> assertTrue(true && true),
                ()-> assertEquals(1,1)
        );
    }

    /**
     * 测试前置条件
     */
    @DisplayName("测试前置条件")
    @Test
    void testassumptions() {
        Assumptions.assumeTrue(true,"结果不是true");
        System.out.println("1111");
    }

}
