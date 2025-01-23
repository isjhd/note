package com.hspedu.tankgame6;
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
    Vector<Node> nodes = new Vector<>();//定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标和方向
    Vector<Bomb> bombs = new Vector<>();//存放炸弹，当子弹击中坦克时，加入一个Bomb对象到bombs
    int enemyTanksSize = 3;//敌人坦克数量

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        nodes = Recorder.getNodesAndEnemyTankRec();
        //将MyPanel对象的enemyTanks设置给Recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        isjhd = new Isjhd(500, 100);//初始化自己的坦克
        isjhd.setSpeed(2);//控制坦克速度

        switch (key) {
            case "1":
                for (int i = 0; i < enemyTanksSize; i++) {//初始化敌人坦克
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);//创建一个敌人的坦克
                    enemyTank.setEnemyTanks(enemyTanks);//将enemyTanks设置给enemyTank
                    enemyTank.setDirect(2);//设置方向

                    new Thread(enemyTank).start();//启动敌人坦克线程，让他动起来

                    Shot shot = new Shot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirect());//给该enemyTank加入一颗子弹
                    enemyTank.shots.add(shot);//加入enemyTank的Vector成员
                    new Thread(shot).start();//启动shot对象

                    enemyTanks.add (enemyTank);//加入
                    Recorder.setAllEnemyTankNum(0);
                }
                break;
            case "2"://继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {//初始化敌人坦克
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());//创建一个敌人的坦克
                    enemyTank.setEnemyTanks(enemyTanks);//将enemyTanks设置给enemyTank
                    enemyTank.setDirect(node.getDirect());//设置方向

                    new Thread(enemyTank).start();//启动敌人坦克线程，让他动起来

                    Shot shot = new Shot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirect());//给该enemyTank加入一颗子弹
                    enemyTank.shots.add(shot);//加入enemyTank的Vector成员
                    new Thread(shot).start();//启动shot对象

                    enemyTanks.add (enemyTank);//加入
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

        //这里，播放指定的音乐
        new AePlayWave("src\\111.wav").start();
    }

    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g) {

        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 1);//画出一个敌方坦克
        g.setColor(Color.BLACK);//这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        showInfo(g);
        if(isjhd != null && isjhd.isLive) {
            //画出坦克-封装方法
            drawTank(isjhd.getX(), isjhd.getY(), g, isjhd.getDirect(), 0);//我的坦克
        }

        //将isjhd的子弹集合shots，遍历取出绘制
        for (int i = 0; i < isjhd.shots.size(); i++) {
            Shot shot = isjhd.shots.get(i);
            //画出isjhd射击的子弹
            if(shot != null && shot.isLive){
                System.out.println("子弹被绘制..");
                g.draw3DRect(shot.x, shot.y,2,2,false);
            } else {//如果该shot对象已经无效，就从shots集合中拿掉
                isjhd.shots.remove(shot);
            }
        }

        //如果bombs集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);//取出炸弹
            if(bomb.life > 6) {//根据当前这个bomb对象的life值去画出对应的图片
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if(bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }

            bomb.lifeDown();//让这个炸弹的生命值减少
            if(bomb.life == 0) {//如果bomb.life为0，就从bombs的集合中删除
                bombs.remove(bomb);
            }
        }

        for (int i = 0; i < enemyTanks.size(); i++) {//画出敌人坦克，遍历Vector
            EnemyTank enemyTank = enemyTanks.get(i);//取出坦克

            if(enemyTank.isLive) {//当敌人坦克是存活的，才画出该坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);

                //画出enemyTank所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);//取出子弹
                    if (shot.isLive) {//绘制
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        enemyTank.shots.remove(shot);//从Vector移除
                    }
                }
            }
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

    //如果我们的坦克可以发射多个子弹
    //在判断我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中
    //所有的子弹，都取出和敌人的所有坦克，进行判断
    public void hitEnemyTank() {

        for (int j = 0; j < isjhd.shots.size(); j++) {
            Shot shot = isjhd.shots.get(j);//取出子弹
            //判断是否击中了敌人的坦克
            if (shot != null && shot.isLive) {//当我的子弹还存活
                for (int i = 0; i < enemyTanks.size(); i++) {//遍历敌人所有的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //编写方法，判断敌人坦克是否击中我的坦克
    public void hitIsjhd() {
        for (int i = 0; i < enemyTanks.size(); i++) {//遍历所有敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);//取出敌人坦克

            for (int j = 0; j < enemyTank.shots.size(); j++) {//遍历enemyTank对象的所有子弹
                Shot shot = enemyTank.shots.get(j);//取出子弹

                if(isjhd.isLive && shot.isLive) {//判断shot是否击中我的坦克
                    hitTank(shot, isjhd);
                }
            }
        }
    }

    //编写方法，判断我方的子弹是否击中敌人坦克
    public void hitTank(Shot s, Tank enemyTank) {
        switch (enemyTank.getDirect()) {//判断s击中坦克
            case 0: //坦克向上
            case 2: //坦克向下
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+40
                    && s.y > enemyTank.getY() && s.y < enemyTank.getY()+60) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);//当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉

                        //当我方击毁一个敌人坦克时， 就对数据allEnemyTankNum++
                        //解读，因为enemyTank可以是Hero也可以是EnemyTank
                        if(enemyTank instanceof EnemyTank) {
                            Recorder.addAllEnemyTankNum();
                        }

                        //创建Bomb对象，加入到bombs集合
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                }
                break;
            case 1: //坦克向右
            case 3: //坦克向左
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+60
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY()+40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);//当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉

                    //当我方击毁一个敌人坦克时， 就对数据allEnemyTankNum++
                    //解读，因为enemyTank可以是Hero也可以是EnemyTank
                    if(enemyTank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }

                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理w、d、s、a 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            isjhd.setDirect(0);// 改变坦克的方向
            if (isjhd.getY() > 0) {
                isjhd.moveUp();//修改坦克坐标
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D键
            isjhd.setDirect(1);
            if (isjhd.getX() + 60 < 1000) {
                isjhd.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S键
            isjhd.setDirect(2);
            if (isjhd.getY() + 60 < 750) {
                isjhd.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A键
            isjhd.setDirect(3);
            if (isjhd.getX() > 0) {
                isjhd.moveLeft();
            }
        }

        //如果用户按下的是J，就发射
        if(e.getKeyCode() == KeyEvent.VK_J) {

            //发射一颗子弹
//            if (isjhd.shot ==null || !isjhd.shot.isLive) {//判断isjhd的子弹是否销毁
//                isjhd.shotEnemyTank();
//            }

            //发射多颗子弹
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

            hitEnemyTank();//判断我方子弹是否击中敌人坦克

            hitIsjhd();//判断敌人子弹是否击中我方坦克

            this.repaint();//让面板重绘
        }
    }
}
