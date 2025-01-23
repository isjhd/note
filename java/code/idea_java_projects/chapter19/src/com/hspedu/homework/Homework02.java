package com.hspedu.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework02 {
    public static void main(String[] args) throws IOException {
        String s = "D:\\story.txt";
        BufferedReader bu = new BufferedReader(new FileReader(s));

        String line;
        int i = 1;
        while ((line = bu.readLine()) != null) {
            System.out.println(i + ": " +line);
            i++;
        }

        bu.close();
    }
}
