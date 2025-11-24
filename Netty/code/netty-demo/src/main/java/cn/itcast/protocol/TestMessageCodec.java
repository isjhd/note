package cn.itcast.protocol;

import cn.itcast.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

public class TestMessageCodec {
    public static void main(String[] args) throws Exception {

        //
        LengthFieldBasedFrameDecoder FRAME_DECODER =
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0);

        LoggingHandler LOGGING_HANDLER = new LoggingHandler();

        EmbeddedChannel channel = new EmbeddedChannel(
            LOGGING_HANDLER,
            FRAME_DECODER,
            new MessageCodec()
        );

        //encode
        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
        //channel.writeOutbound(message);

        //decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        //入栈
        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100, buf.readableBytes() - 100);
        s1.retain();//引用计数2
        channel.writeInbound(s1);//release 1
        channel.writeInbound(s2);

    }
}
