package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;


/* @author  i-s-j-h-d
 * @version 1.0
 * 该类/对象，提供和消息相关的服务方法
 */
public class MessageClientService {
    /*
       @param content 内容
       @param senderId 发送者
    */
    public void sendMessageToAll(String content, String senderID) {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//群发的聊天消息这种类型
        message.setSender(senderID);
        message.setContent(content);
        message.setSendTime(new Date().toString());//发送时间设置到message对象
        System.out.println(senderID + " 对大家说 " + content);
        //发送服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (senderID).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
       @param content 内容
       @param senderId 发送用户id
       @param getterId 接收用户id
    */
    public void sendMessageToOne(String content, String senderID, String getterID) {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);//普通的聊天消息这种类型
        message.setSender(senderID);
        message.setGetter(getterID);
        message.setContent(content);
        message.setSendTime(new Date().toString());//发送时间设置到message对象
        System.out.println(senderID + " 对 " + getterID + " 说 " + content);
        //发送服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (senderID).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void offlineMessage(String userID) {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.OFFLINE_MESSAGE);//普通的聊天消息这种类型
        message.setGetter(userID);

        //发送给服务器
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                    (userID).getSocket().getOutputStream());
            oos.writeObject(message);//发送一个Message对象，向服务端要求在线用户列表
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
