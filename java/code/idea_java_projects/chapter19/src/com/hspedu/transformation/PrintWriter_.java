package com.hspedu.transformation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示PrintWriter使用方式
 */
public class PrintWriter_ {
    public static void main(String[] args) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("D:\\f2.txt"));
        printWriter.print("hi~");
        printWriter.close();//关闭流，才会将数据写入到文件...
    }
}
