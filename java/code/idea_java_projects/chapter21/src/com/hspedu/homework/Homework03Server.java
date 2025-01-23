package com.hspedu.homework;

import com.hspedu.upload.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/* @author  i-s-j-h-d
 * @version 1.0
 * 服务端
 */
public class Homework03Server {
    public static void main(String[] args) throws Exception {
        //思路：
        //1. 在本机的9999端口监听，等待连接
        //细节：要求在本机没有其它服务在监听9999
        //细节：这个ServerSocket可以通过accept()返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(8999);
        System.out.println("服务端，在8999端口监听，等待连接...");

        //2.当没有客户端连接9999端口时，程序会阻塞，等待连接
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket=" + socket.getClass());

        //3. 通过socket.getInputStream() 读取客户端写入到数据通道的数据， 显示
        InputStream inputStream = socket.getInputStream();

        //4. IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        String music = "";
        while ((readLen = inputStream.read(buf)) != -1) {
            music = new String(buf, 0, readLen);//根据读取到的实际长度，显示内容
        }

        //5. 获取socket相关联的输出流
        //创建读取磁盘文件的输入流
        String filePath = "";
        if (music.equals("kon")) {
            filePath = "src\\kon.ogg";
        } else {
            filePath = "src\\kon2.ogg";
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        //byte [] bytes = StreamUtils.streamToByteArray(bis);
        OutputStream bos = socket.getOutputStream();
        byte [] bytes = new byte[1024];
        int i;
        while ((i = bis.read(bytes)) != -1){
            bos.write(bytes, 0, i);
        }
        //设计写入结束标记
        socket.shutdownOutput();

        //6. 关闭流和socket
        bos.close();
        bis.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
