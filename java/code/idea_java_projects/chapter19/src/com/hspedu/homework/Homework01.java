package com.hspedu.homework;

import java.io.*;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework01 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("D:\\mytemp");
        if(!(file1.exists())) {
            file1.mkdir();
        }

        file1 = new File("D:\\mytemp\\hello.txt");
        if(!(file1.exists())) {
            file1.createNewFile();
        } else {
            System.out.println("该文件已存在");
        }

        String s = "D:\\mytemp\\hello.txt";
        BufferedWriter bu = new BufferedWriter(new FileWriter(s));
        bu.write("hello, world");

        bu.close();

    }
}
