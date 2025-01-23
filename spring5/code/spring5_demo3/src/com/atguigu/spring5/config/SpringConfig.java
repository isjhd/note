package com.atguigu.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Configuration // 作为配置类，代替xml配置文件
@ComponentScan(basePackages={"com.atguigu"})
public class SpringConfig {
}
