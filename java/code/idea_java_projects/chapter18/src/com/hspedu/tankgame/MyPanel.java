package com.hspedu.tankgame;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了监听 键盘事件， 实现KeyListener
//为了让Panel不停的重绘子弹，需要将MyPanel实现Runnable,当做一个线程使用
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Isjhd isjhd = null;//定义我的坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();//敌人坦克
    int enemyTanksSize = 3;//敌人坦克数量

    public MyPanel() {
        isjhd = new Isjhd(100, 100);//初始化自己的坦克
        isjhd.setSpeed(2);//控制坦克速度

        for (int i = 0; i < enemyTanksSize; i++) {
            enemyTanks.add(new EnemyTank(100*(i + 1), 0 ));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出坦克-封装方法
        drawTank(isjhd.getX(), isjhd.getY(), g, isjhd.getDirect(), 0);//我的坦克

        //画出isjhd射击的子弹
        if(isjhd.shot != null && isjhd.shot.isLive == true){
            System.out.println("子弹被绘制..");
            g.draw3DRect(isjhd.shot.x, isjhd.shot.y,2,2,false);
        }

        for (int i = 0; i < enemyTanks.size(); i++) {//敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.setDirect(2);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
        }
    }

    //编写方法，画出坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        /*
         * X 坦克的左上角x坐标
         * y 坦克的左上刺坐标
         * g 画笔
         * direct  坦克方向（上下左右）
         * type  坦克类型
         * */


        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0: //我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敌人的坦克的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克方向，来绘制坦克
        //direct 表示方向(0向上, 1向右, 2向下, 3向左)
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理w、d、s、a 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            isjhd.setDirect(0);// 改变坦克的方向
            isjhd.moveUp();//修改坦克坐标
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D键
            isjhd.setDirect(1);
            isjhd.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S键
            isjhd.setDirect(2);
            isjhd.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A键
            isjhd.setDirect(3);
            isjhd.moveLeft();
        }

        //如果用户按下的是J，就发射
        if(e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J，开始射击.");
            isjhd.shotEnemyTank();
        }

        this.repaint();//让面板重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒，重绘区域，刷新绘图区域，子弹就移动

        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();//让面板重绘
        }
    }
}
