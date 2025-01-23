package com.hspedu.qqserver.server;

import com.hspedu.qqcommon.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* @author  i-s-j-h-d
 * @version 1.0
 * 该类用于管理和客户端通信的线程
 */
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    private static ConcurrentHashMap<String, ArrayList<Message>> offLineDb = new ConcurrentHashMap<>();//离线通信

    public static ArrayList<Message> getOffLineDb(String userID) {
        return offLineDb.get(userID);
    }

    public static void setOffLineDb(String userID, ArrayList<Message> messages) {
        offLineDb.put(userID, messages);
    }

    //添加线程对象到hm集合
    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    //添加线程对象到hm集合
    public static void addClientThread(String userID, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userID, serverConnectClientThread);
    }

    //根据userID返回ServerConnectClientThread线程
    public static ServerConnectClientThread getServerConnectClientThread(String userID) {
        return hm.get(userID);
    }

    //增加一个方法，从集合中，移除某个线程对象
    public static void removeServerConnectClientThread(String userID) {
        hm.remove(userID);
    }

    //这里编写方法，可以返回在线用户列表
    public static String getOnlineUser() {
        //集合遍历，遍历hashMap的key
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()) {
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }
}
