package com.atguigu.boot09.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* @author  i-s-j-h-d
 * @version 1.0 */

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner....run...");
    }
}
