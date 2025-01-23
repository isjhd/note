package com.hspedu.qqserver.server;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* @author  i-s-j-h-d
 * @version 1.0
 * 这就是服务端，在监听9999，等待客户端的连接，并保持通信
 */
public class QQServer {

    private ServerSocket ss = null;
    //创建一个集合，存放多个用户,如果是这些用户登录，就认为是合法
    //这里我们也可以使用 ConcurrentHashMap，可以处理并发的集合，没有线程安全
    //HashMap没有处理线程安全，因此在多线程情况下是不安全
    //ConcurrentHashMap处理的线程安全，即线程同步处理，在多线程情况下是安全
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();


    static {//在静态代码块，初始化validUsers
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("至尊宝", new User("至尊宝", "123456"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("菩提老祖", new User("菩提老祖", "123456"));
    }

    //验证用户是否有效的方法
    private boolean checkUser(String userID, String passwd) {
        User user = validUsers.get(userID);
        //过关的验证方法
        if(user == null) {//说明userID没有存在validUsers的key中
            return false;
        }
        if(!user.getPasswd().equals(passwd)) {//userID正确，但是密码错误
            return false;
        }
        return true;
    }

    public QQServer() {
        //注意：端口可以写在配置文件
        try {
            System.out.println("服务端在9999端口监听...");
            new Thread(new SendNewsToAllService()).start();//启动推送新闻的线程
            ss = new ServerSocket(9999);

            while (true) {//当和某个客户端连接后，会继续监听，因此while
                Socket socket = ss.accept();//如果没有客户端连接，就会阻塞在这
                //得到socket关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //得到socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User u = (User)ois.readObject();//读取客户端发送的User对象
                //创建一个Message对象，准备回复客户端
                Message message = new Message();
                //验证用户，写个方法
                if (checkUser(u.getUserID(), u.getPasswd())) {//登陆成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //将message对象回复客户端
                    oos.writeObject(message);
                    //创建一个线程，和客户端保持通信，该线程需要持有socket对象
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, u.getUserID());
                    //启动线程
                    serverConnectClientThread.start();
                    //把线程对象，放入到一个集合中，进行管理
                    ManageClientThreads.addClientThread(u.getUserID(), serverConnectClientThread);

                } else {//登陆失败
                    System.out.println("用户 id=" + u.getUserID() + "pwd=" + u.getPasswd() + "验证失败。");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    //关闭socket
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //如果服务器退出了while，说明服务器端不在监听，因此关闭ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
