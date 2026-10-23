package cn.itcast.message;

public class PingMessage extends Message {

    @Override
    public int getMessageType() {
        return 0;
    }
}

