package com.hspedu.tankgame5;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Bomb {//炸弹
    int x,y; //炸弹的坐标
    int life = 9; //炸弹的生命周期
    boolean isLive = true; //是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {//减少生命值，配合出现图片的爆炸效果
        if(life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
