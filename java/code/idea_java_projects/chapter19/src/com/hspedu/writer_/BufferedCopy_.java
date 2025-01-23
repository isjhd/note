package com.hspedu.writer_;

import java.io.*;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class BufferedCopy_ {
    public static void main(String[] args) throws IOException {
        //1. BufferedReader和BufferedWriter是按照字符操作
        //2. 不要去操作二进制文件，可能造成文件损坏
        String srcFilePath = "D:\\story.txt";
        String destFilePath = "D:\\story2.txt";
        BufferedReader br = new BufferedReader(new FileReader(srcFilePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destFilePath, true));
        String line;

        //说明：readLine读取一行内容，但是没有换行
        while ((line = br.readLine()) != null) {
            bw.write(line);//每读入一行，就写入
            bw.newLine();//插入一个就换行
        }

        //关闭流
        br.close();
        bw.close();

    }
}
