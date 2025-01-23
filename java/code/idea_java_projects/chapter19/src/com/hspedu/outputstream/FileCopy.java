package com.hspedu.outputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class FileCopy {
    public static void main(String[] args) {
        //完成文件拷贝，将D:\\music.jpg拷贝c:\\
        //思路分析
        //1.创建文件的输入流，将文件读入到程序
        //2.创建文件的输出流，将读取到的文件数据，写入到指定的文件

        //在完成程序时，应该是读取部分数据，
        //就写入到指定文件，这里使用循环

        String srcFilePath = "D:\\story.txt";
        String desFilePath = "D:\\story1.txt";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(srcFilePath);
            fileOutputStream = new FileOutputStream(desFilePath);

            byte[] buf = new byte[1024];//定义一个字节数组，提高读取效果
            int readLen = 0;
            while ((readLen = fileInputStream.read(buf)) != -1) {
                //读取到后，就写入到文件 通过 fileOutputStream
                //即，是一边读，一边写
                fileOutputStream.write(buf, 0, readLen);//一定要用这个方法
            }

            System.out.println("ok~");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭输入流和输出流，释放资源
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
