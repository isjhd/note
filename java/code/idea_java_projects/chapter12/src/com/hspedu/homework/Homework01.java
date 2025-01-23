package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework01 {
    public static void main(String[] args) {

        String n [] = new String[2];
        n[0] = "10";
        n[1] = "5";

        try {
            if (n.length != 2) {
                throw new ArrayIndexOutOfBoundsException("缺少命令行参数");
            }

            int n3 = Integer.parseInt(n[0]);
            int n4 = Integer.parseInt(n[1]);
            EcmDef.cal(n3, n4);
        } catch(RuntimeException e) {
            if (e instanceof NumberFormatException) {
                System.out.println("数据格式不正确");
            } else if (e instanceof ArrayIndexOutOfBoundsException) {
                System.out.println(e.getMessage());
            } else if (e instanceof ArithmeticException) {
                System.out.println("数学运算异常");
            }
        }
    }
}
class EcmDef {
    public static void cal(int n1, int n2) {
        System.out.println(n1 / n2);
    }

}
