package com.hspedu.qqserver.server;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.utils.Utility;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class SendNewsToAllService implements Runnable{

    @Override
    public void run() {

        while(true) {//为了可以推送多次新闻，使用while
            System.out.println("请输入服务器要推送的新闻/消息[输入exit表示退出推送服务]");
            String news = Utility.readString(100);
            if("exit".equals(news)) {
                break;
            }
            //构建一个消息，群发消息
            Message message = new Message();
            message.setSender("服务器");
            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
            message.setContent(news);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人 说：" + news);

            //遍历当前所有的通信线程，得到socket，并发送message
            HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
            Iterator<String> iterator = hm.keySet().iterator();
            while (iterator.hasNext()) {
                String onLineUserID = iterator.next().toString();
                try {
                    ObjectOutputStream oos =
                            new ObjectOutputStream(hm.get(onLineUserID).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
