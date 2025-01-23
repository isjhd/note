package com.atguigu.spring5.collectiontype;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Orders {

    // 无参构造器
    public Orders() {
        System.out.println("第一步 执行无参数构造创建 bean 实例");
    }

    private String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用 set 方法设置属性值");
    }

    // 创建执行的初始化的方法
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法");
    }
    // 创建执行的销毁的方法
    public void destroyMethod() {
        System.out.println("第五步 执行销毁的方法");
    }
}
