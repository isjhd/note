package com.atguigu.proxy.staticproxy;


public class TeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target; //目标对象，通过接口来聚合

    //构造器
    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理，完成某些操作。。。");
        target.teach();
        System.out.println("提交。。。");
    }
}
