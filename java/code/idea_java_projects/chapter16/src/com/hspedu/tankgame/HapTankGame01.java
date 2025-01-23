package com.hspedu.tankgame;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import javax.swing.*;

public class HapTankGame01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        HapTankGame01 hapTankGame01 = new HapTankGame01();
    }

    public HapTankGame01() {
        mp = new MyPanel();
        this.add(mp);//面板(游戏的绘图区域)
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
