package com.hspedu.writer_;

import java.io.FileWriter;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class FileWriter_ {
    public static void main(String[] args) {

        String filePath = "D:\\note.txt";
        FileWriter fileWriter = null;//创建FileWriter对象
        char[] chars = {'a', 'b', 'c'};
        try {
            fileWriter = new FileWriter(filePath);

            //write(int): 写入单个字符
            fileWriter.write('h');

            //write(char[]): 写入指定数组
            fileWriter.write(chars);

            //write(char[], off, len): 写入指定数组的指定部分
            fileWriter.write("碧蓝之海".toCharArray(), 0, 2);

            //write(string): 写入整个字符串
            fileWriter.write("轻音少女");

            //write(string, off, len): 写入字符串的指定部分
            fileWriter.write("fate", 0, 2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //对应FileWriter，一定要关闭流，或者flush才能真正的把数据写入到文件
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
