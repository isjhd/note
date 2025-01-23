package com.atguigu.boot09.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class MyApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyApplicationContextInitializer ...onApplicationEvent...");
    }
}
