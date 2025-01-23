package com.atguigu.proxy.dynamic;

import java.sql.SQLOutput;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("老师正在授课中。。。");
    }
}
