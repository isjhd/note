package com.hspedu.transformation;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示 OutputStreamWriter 使用
 * 把FileOutputStream 字节流，转成字符流 OutputStreamWriter
 * 指定处理的编码 gbk/utf-8
 *  */
public class OutputStreamWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\hello.txt";
        String charSet = "utf-8";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), charSet);
        osw.write("轻音少女");
        osw.close();
        System.out.println("按照" + charSet + "保存文件成功~");
    }
}
