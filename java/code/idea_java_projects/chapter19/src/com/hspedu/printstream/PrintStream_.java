package com.hspedu.printstream;

import java.io.IOException;
import java.io.PrintStream;

/* @author  i-s-j-h-d
 * @version 1.0
 *
 * 演示PrintStream(字节打印流)
 */
public class PrintStream_ {
    public static void main(String[] args) throws IOException {

        PrintStream out = System.out;
        //在默认情况下，PrintStream输出数据的位置是标准输出，即显示器
        out.print("john, hello");
        //因为print底层使用的是write，所以我们可以直接调用write进行打印/输出
        out.write("你好".getBytes());
        out.close();

        //我们可以去修改打印流输出的位置/设备
        //1. 输出修改成到"D:\\f1.txt"
        //2. "hello"就会输出到D:\\f1.txt
        System.setOut(new PrintStream("D:\\f1.txt"));
        System.out.println("hello");
    }
}
