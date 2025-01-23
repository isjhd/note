package com.hspedu.try_;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class TryCatchExercise04 {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        String inputStr = "";
        do {
            System.out.print("请输入一个整数：");
            inputStr = scanner.next();
            try {
                num = Integer.parseInt(inputStr);
                loop = false;
            } catch(NumberFormatException e) {
                System.out.println("输入有误，请重新输入...");
            }
        } while(loop);
    }
}
