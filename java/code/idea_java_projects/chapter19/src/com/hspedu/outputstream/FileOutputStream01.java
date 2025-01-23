package com.hspedu.outputstream;

import com.hspedu.inputstream_.FileInputStream_;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class FileOutputStream01 {
    public static void main(String[] args) {

    }

    /*
     演示使用FileOutputStream 将数据写到文件中,
     如果该文件不存在，则创建该文件
    */
    @Test
    public void writeFile() {

        //创建FileOutputStream对象
        String filePath = "D:\\a.txt";
        FileOutputStream fileOutputStream = null;

        try {
            //得到FileOutputStream对象
            //1. new FileOutputStream(filePath)创建方式，当写入内容是，会覆盖原来的内容
            //2. new FileOutputStream(filePath,true)创建方式，当写入内容是，是追加到文件后面
            fileOutputStream = new FileOutputStream(filePath);

            //fileOutputStream.write('h');//写入一个字节

            String str = "hello,world!";//写入字符串
            fileOutputStream.write(str.getBytes());//str.getBytes()可以把字符串->字节数组

            //write(byte[] b， int off， int len）
            // 将 len字节从位于偏移量 off的指定字节数组写入此文件输出流
            fileOutputStream.write(str.getBytes(), 0, 3);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭文件留，释放资源
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
