package com.hspedu.writer_;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class BufferedWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\note.txt";
        //创建BufferedWriter
        //说明：
        //1. new FileWriter(filePath, true)表示以追加的方式写入
        //2. new FileWriter(filePath), 表示以覆盖的方式写入
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.newLine();//插入一个和系统相关的换行
        bufferedWriter.write("你好，轻音少女");

        //说明：关闭外层流即可，传入的new FileWriter(filePath)，会在底层关闭
        bufferedWriter.close();
    }
}
