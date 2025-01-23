package com.atguigu.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent{

    //List 中 存放的是Department
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }

    //重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        // 将来实际业务中，Collage 的 add 和 University add 不一定完全一样
        organizationComponents.add(organizationComponent);
    }

    //重写remove
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }

    // print方法，就是输出University 包含的学院
    @Override
    protected void print() {
        System.out.println("===================" + getName() + "===================");
        //遍历 organizationComponents
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
    }

}
