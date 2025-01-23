package com.hspedu.outputstream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示ObjectOutputStream的使用，完成数据的序列化
 * */
public class ObjectOutStream {
    public static void main(String[] args) throws Exception{
        //序列化后，保存的文件格式，不是存文本，而是按照他的格式来保存
        String filePath = "D:\\data.dat";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));

        //序列化数据到D:\\data.dat
        oos.writeInt(100);//int->Integer(实现了Serializable)
        oos.writeBoolean(true);//boolean->Boolean(实现了Serializable)
        oos.writeChar('a');//char->Character(实现了Serializable)
        oos.writeDouble(9.5);//double->Double(实现了Serializable)
        oos.writeUTF("韩顺平教育");//String

        //保存一个dog对象
        oos.writeObject(new Dog("旺财", 10));

        oos.close();
        System.out.println("数据保存完毕(序列化形式)");
    }
}