package com.atguigu.spring5_;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Book {

    //创建属性
    private String bname;
    private String bauthor;
    private String address;
    //创建属性对应的 set 方法
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void testBook() {
        System.out.println(bname + "，" + bauthor + "，" + address);
    }
}
