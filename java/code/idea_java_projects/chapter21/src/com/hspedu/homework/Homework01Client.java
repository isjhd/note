package com.hspedu.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/* @author  i-s-j-h-d
 * @version 1.0
 * 客户端
 */
public class Homework01Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1.连接服务端（ip，端口
        //解读：连接本机的 8999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8999);
        System.out.println("客户端 socket返回=" + socket.getClass());

        //2. 连接上后，生成Socket，通过socket.getOutputStream()
        //得到和socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3. 通过输出流，写入数据到数据通道，使用字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("name");
        bufferedWriter.newLine();//插入一个换行符，表示写入的内容结束，注意，要求对方使用readLine()
        bufferedWriter.flush();//如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

        //4. 获取和socket关联的输入流，读取数据(字节)，并显示
        InputStream inputStream = socket.getInputStream();
        //IO读取，使用字符流，老师使用 InputStreamReader 将 inputStream 转成字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);//输出


        //5. 关闭流对象和socket， 必须关闭
        bufferedReader.close();//关闭外层流
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
