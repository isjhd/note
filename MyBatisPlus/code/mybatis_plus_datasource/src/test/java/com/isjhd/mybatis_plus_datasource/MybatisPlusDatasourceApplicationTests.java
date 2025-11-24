package com.isjhd.mybatis_plus_datasource;

import com.isjhd.mybatis_plus_datasource.service.ProductService;
import com.isjhd.mybatis_plus_datasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusDatasourceApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void test() {
        System.out.println(userService.getById(1));
        System.out.println(productService.getById(1));
    }

}
