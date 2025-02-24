package com.hspedu.reader_;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class FileReader_ {
    public static void main(String[] args) {

    }

    /*
    * 单个字符读取文件
    */
    @Test
    public void readFile01() {
        String filePath = "D:\\story.txt";
        FileReader fileReader = null;
        int data = 0;
        try {
            //创建FileReader对象
            fileReader = new FileReader(filePath);

            //循环读取，使用read，单个字符读取
            while ((data = fileReader.read()) != -1) {
                System.out.print((char)data);//转换成char显示
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    * 字符数组读取文件
    */
    @Test
    public void readFile02() {
        String filePath = "D:\\story.txt";
        FileReader fileReader = null;

        int readLen = 0;
        char[] buf = new char[8];
        try {
            //创建FileReader对象
            fileReader = new FileReader(filePath);

            //循环读取，使用read(buf)，返回的是实际读取到的字符数
            //如果返回-1，说明到文件结束
            while ((readLen = fileReader.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
