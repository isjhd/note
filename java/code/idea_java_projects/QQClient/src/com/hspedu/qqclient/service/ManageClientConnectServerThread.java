package com.hspedu.qqclient.service;

import java.util.HashMap;

/* @author  i-s-j-h-d
 * @version 1.0
 * 该类管理客户端连接到服务端的线程的类
 */
public class ManageClientConnectServerThread {
    //我们把多个线程放入一个HashMap集合，key就是用户id，value就是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    //将某个线程加入到集合
    public static void addClientConnectServerThread(String userID, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userID, clientConnectServerThread);
    }

    //通过userID可以得到对应线程
    public static ClientConnectServerThread getClientConnectServerThread(String userID) {
        return hm.get(userID);
    }
}
