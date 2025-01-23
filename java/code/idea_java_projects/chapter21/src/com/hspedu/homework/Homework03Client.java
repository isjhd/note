package com.hspedu.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/* @author  i-s-j-h-d
 * @version 1.0
 * 客户端
 */
public class Homework03Client {
    public static void main(String[] args) throws Exception {
        //思路
        //1.连接服务端（ip，端口
        //解读：连接本机的 9999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8999);
        System.out.println("客户端 socket返回=" + socket.getClass());

        //2. 连接上后，生成Socket，通过socket.getOutputStream()
        //得到和socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3. 通过输出流，写入数据到数据通道
        outputStream.write("kon".getBytes());
        //设计写入结束标记
        socket.shutdownOutput();

        //4. 获取和socket关联的输入流，读取数据(字节)，并显示
        InputStream bos = socket.getInputStream();
        String filePath = "D:\\music.ogg";
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(filePath));
        byte [] bytes = new byte[1024];
        int i;
        while ((i = bos.read(bytes)) != -1){
            bis.write(bytes, 0, i);
        }


        //5. 关闭流对象和socket， 必须关闭
        outputStream.close();
        bis.close();
        bos.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
