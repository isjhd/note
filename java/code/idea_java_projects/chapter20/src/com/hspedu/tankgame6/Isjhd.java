package com.hspedu.tankgame6;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.Vector;

public class Isjhd extends Tank {

    Shot shot = null;//定义一个Shot对象，表示一个射击(线程)
    Vector<Shot> shots = new Vector<>();//可以发射多颗子弹
    public Isjhd(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {

        //发多颗子弹怎么办，控制在我们的面板上，最多只有5颗
        if(shots.size() == 5){
            return;
        }

        //创建 Shot 对象，根据当前Hero对象的位置和方向来创建Shot
        switch (getDirect()) {//得到isjhd对象方向
            case 0://向上
                shot = new Shot(getX()+20, getY(), 0);
                break;
            case 1://向右
                shot = new Shot(getX()+60, getY()+20, 1);
                break;
            case 2://向下
                shot = new Shot(getX()+20, getY()+60, 2);
                break;
            case 3://向左
                shot = new Shot(getX(), getY()+20, 3);
                break;
        }

        shots.add(shot);//把新创建的shot放入到shots
        new Thread(shot).start();//启动我们的Shot线程
    }
}
