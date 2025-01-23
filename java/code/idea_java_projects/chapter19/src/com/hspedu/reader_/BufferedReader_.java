package com.hspedu.reader_;

import java.io.BufferedReader;
import java.io.FileReader;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class BufferedReader_ {
    public static void main(String[] args) throws Exception{

        String filePath = "D:\\story.txt";

        //创建bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String line;//按行读取，效率高
        //说明
        //1. bufferedReader.readLine()是按行读取文件
        //2. 当返回null时，表示文件读取完毕
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        //关闭流，这里注意，只需要关闭BufferedReader,因为底层会自动的去关闭节点流
        bufferedReader.close();
    }
}
