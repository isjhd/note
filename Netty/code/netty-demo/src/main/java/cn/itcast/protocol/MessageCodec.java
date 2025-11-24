package cn.itcast.protocol;

import cn.itcast.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override//编码
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //1. 四个字节的魔数
        out.writeBytes(new byte[]{1,2,3,4});
        //2. 一个字节的版本
        out.writeByte(1);
        //3. 一个字节的序列化方式 jdk 0, json 1
        out.writeByte(0);
        //4. 一个字节的指令类型
        out.writeByte(msg.getMessageType());
        //5. 四个字节，为了双工通信，提供异步能力
        out.writeInt(msg.getSequenceId());
        //无意义，对齐填充，满足2的整数倍
        out.writeByte(0xff);

        //6. 获取内容的字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        //7. 长度
        out.writeInt(bytes.length);

        //8. 写入内容
        out.writeBytes(bytes);

    }

    @Override//解码
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();//字节的魔数
        byte version = in.readByte();//字节的版本
        byte serializerType = in.readByte();//字节的序列化方式
        byte messageType = in.readByte();//字节的指令类型
        int sequenceId = in.readInt();//为了双工通信，提供异步能力
        in.readByte();//无意义字节
        int length = in.readInt();//正文内容长度
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);//正文内容
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        log.debug("{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        out.add(message);
    }
}