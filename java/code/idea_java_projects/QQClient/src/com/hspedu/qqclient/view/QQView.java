package com.hspedu.qqclient.view;

import com.hspedu.qqclient.service.FileClientService;
import com.hspedu.qqclient.service.MessageClientService;
import com.hspedu.qqclient.service.UserClientService;
import com.hspedu.qqclient.utils.Utility;

/* @author  i-s-j-h-d
 * @version 1.0
 * 客户端的菜单界面
 */
public class QQView {

    private boolean loop = true;//控制是否显示菜单
    private String key = "";//接收用户的键盘输入
    private UserClientService userClientService = new UserClientService();//对象是用于登录服务/注册用户
    private MessageClientService messageClientService = new MessageClientService();//对象用户私聊/群聊
    private FileClientService fileClientService = new FileClientService();//该对象用户传输文件
    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("客户端退出系统...");
    }

    //显示主菜单
    private void mainMenu() {

        while (loop) {
            System.out.println("=======欢迎登录网络通信系统============");
            System.out.println("\t\t 1、登录系统");
            System.out.println("\t\t 9、退出系统");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);

            //根据用户的输入，来处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.print("请输入用户号：");
                    String userID = Utility.readString(50);
                    System.out.print("请输入密  码：");
                    String pwd = Utility.readString(50);
                    //这里就比较麻烦了，需要到服务器去验证该用户是否合法
                    //这里有很多代码，我们这里编写一个类UserClientService[用户登录/注册]

                    if(userClientService.checkUser(userID, pwd)) {
                        //编写方法，离线消息
                        messageClientService.offlineMessage(userID);

                        System.out.println("===========欢迎 (用户 " + userID + " 登陆成功) ================");
                        //进入到二级菜单
                        while (loop) {
                            System.out.println("\n============网络通信系统二级菜单(用户 " + userID + " )==============");
                            System.out.println("\t\t 1、显示在线用户列表");
                            System.out.println("\t\t 2、群发消息");
                            System.out.println("\t\t 3、私聊消息");
                            System.out.println("\t\t 4、发送文件");
                            System.out.println("\t\t 9、退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话：");
                                    String s = Utility.readString(100);
                                    //调用一个方法，将消息封装成message对象，发送给服务端
                                    messageClientService.sendMessageToAll(s, userID);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的用户号：");
                                    String getterID = Utility.readString(50);
                                    System.out.print("请输入想说的话：");
                                    String content = Utility.readString(100);
                                    //编写一个方法，将消息发送给服务端
                                    messageClientService.sendMessageToOne(content, userID, getterID);
                                    break;
                                case "4":
                                    System.out.print("请输入你想把文件发送给的用户：");
                                    getterID = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径(形式 d:\\xx.jpg)");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入把文件发送到对应的路径(形式 d:\\yy.jpg)");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFileToOne(src, dest, userID, getterID);
                                    break;
                                case "9":
                                    //调用方法，给服务器发送一个退出系统的message
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {//登录服务器失败
                        System.out.println("==============登陆失败===============");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
