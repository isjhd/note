package com.atguigu.maven.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class MavenTest {

    @Test
    public void testAssert(){
        int a = 10;
        int b = 20;
        Assertions.assertEquals(a+b, 30);
    }

}
