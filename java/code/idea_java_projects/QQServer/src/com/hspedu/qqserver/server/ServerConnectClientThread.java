package com.hspedu.qqserver.server;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* @author  i-s-j-h-d
 * @version 1.0
 * 该类的一个对象和某个客户端保持通信
 */
public class ServerConnectClientThread extends Thread{

    private Socket socket;
    private String userID;//连接到服务端的用户ID

    public ServerConnectClientThread(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {//这里线程处于run的状态，可以发送/接收消息

        while (true) {
            try {
                System.out.println("服务端和客户端" + userID + "保持通信，读取数据...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                //后面会使用message，根据message的类型，做相应的业务处理
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    //客户端要在线用户列表
                    /*
                    在线用户列表形式 100 200 紫霞仙子
                    */
                    System.out.println(message.getSender() + " 要在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    //返回message
                    //构建一个Message对象，返回给客户端
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message2.setContent(onlineUser);
                    message2.setGetter(message.getSender());
                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);

                } else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {

                    if(ManageClientThreads.getServerConnectClientThread(message.getGetter()) == null) {
                        ArrayList<Message> messages = new ArrayList<>();
                        messages.add(message);
                        ManageClientThreads.setOffLineDb(message.getGetter(), messages);
                        System.out.println("存入离线...");
                    } else {
                        //根据message获取getterID，然后再得到对应的线程
                        ServerConnectClientThread serverConnectClientThread =
                                ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        //得到对应socket的对象输出流，将message对象转发给指定的客户端
                        ObjectOutputStream oos =
                                new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(message);
                    }
                } else if(message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    //需要遍历管理线程的集合，把所有的线程的socket得到，然后把message进行转发即可
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出在线用户id
                        String onLineUserID = iterator.next().toString();

                        if (!onLineUserID.equals(message.getSender())) {//排除群发消息的这个用户
                            //进行转发message
                            ObjectOutputStream oos =
                                    new ObjectOutputStream(hm.get(onLineUserID).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }

                } else if(message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    //根据getterID获取到对应的线程，将message对象转发
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(message.getGetter());
                    ObjectOutputStream oos =
                            new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);

                } else if(message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {//客户端退出
                    System.out.println(message.getSender() + " 退出");
                    //将这个客户端对应线程，从集合删除
                    ManageClientThreads.removeServerConnectClientThread(message.getSender());
                    socket.close();//关闭连接
                    break;//退出线程
                } else if(message.getMesType().equals(MessageType.OFFLINE_MESSAGE)) {
                    if(ManageClientThreads.getOffLineDb(message.getGetter()) != null) {
                        ArrayList<Message> offLineDb = ManageClientThreads.getOffLineDb(message.getGetter());
                        //根据message获取getterID，然后再得到对应的线程
                        ServerConnectClientThread serverConnectClientThread =
                                ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        //得到对应socket的对象输出流，将message对象转发给指定的客户端
                        ObjectOutputStream oos =
                                new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        for (Message messages: offLineDb) {
                            oos.writeObject(messages);
                        }
                    }
                } else {
                    System.out.println("其他类型，暂时不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
