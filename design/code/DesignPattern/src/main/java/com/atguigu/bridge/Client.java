package com.atguigu.bridge;

public class Client {

    public static void main(String[] args) {
        //获取折叠式手机（样式+品牌）

        Phone phone1 = new FoldedPhone(new XiaoMi());

        phone1.open();
        phone1.call();
        phone1.close();

        Phone phone2 = new FoldedPhone(new Vivo());

        phone2.open();
        phone2.call();
        phone2.close();

        Phone phone3 = new UpRightPhone(new XiaoMi());

        phone3.open();
        phone3.call();
        phone3.close();

    }

}
