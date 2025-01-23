package com.atguigu.java;

/* @author  i-s-j-h-d
 * @version 1.0 */

/**
 * 1.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 * 2.以前用匿名实现类表示的现在都可以用Lambda表达式来写。
 *
 */
@FunctionalInterface// 自定义函数式接口
public interface MyInterface {

    void method1();

}
