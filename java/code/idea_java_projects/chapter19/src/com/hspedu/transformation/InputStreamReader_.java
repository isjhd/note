package com.hspedu.transformation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示使用 InputStreamReader 转换流解决中文乱码问题
 * 将字节流 FileInputStream 转成字符流 InputStreamReader，指定编码 gbk/utf-8
 * */
public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {

        String filePath = "D:\\note.txt";

        //1. 把FileInputStream转成InputStreamReader
        //2. 指定编码gbk
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "utf-8");

        //3. 把InputStreamReader传入BufferedReader
        BufferedReader br = new BufferedReader(isr);

        //4. 读取
        String s = br.readLine();
        System.out.println("读取内容=" + s);

        //5. 关闭外层流
        br.close();
    }
}
