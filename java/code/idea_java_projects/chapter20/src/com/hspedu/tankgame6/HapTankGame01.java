package com.hspedu.tankgame6;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class HapTankGame01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        HapTankGame01 hapTankGame01 = new HapTankGame01();
    }

    public HapTankGame01() {
        System.out.println("请输入选择 1：新游戏 2：继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);

        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//面板(游戏的绘图区域)

        this.setSize(1500, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame申增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    Recorder.keepRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }
}
