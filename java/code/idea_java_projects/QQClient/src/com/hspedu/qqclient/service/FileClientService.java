package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* @author  i-s-j-h-d
 * @version 1.0
 * 该类/对象完成文件传输服务
 */
public class FileClientService {
    /*
        @param src  源文件
        @param dest 把该文件传输到对方的哪个目录
        @param senderId 发送用户id
        @param getterId 接收用户id
    */
    public void sendFileToOne(String src, String dest, String senderID, String getterID) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderID);
        message.setGetter(getterID);
        message.setSrc(src);
        message.setDest(dest);

        //需要将文件读取
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int)new File(src).length()];

        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将src文件读入到程序的字节数组
            //将文件对应的字节数组设置message
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //提示信息
        System.out.println("\n" + senderID + " 给 " + getterID + " 发送文件：" + src
                + " 到对方的电脑的目录" + dest);

        //发送
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread
                            (senderID).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
