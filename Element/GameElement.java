package ccy03.TankGame.Tankgame_v1.Element;

import java.awt.*;

public abstract class GameElement {
    protected int x,y;
    protected int width,height;
    protected int speedx,speedy;
    protected boolean isAlive;

    public GameElement(int x, int y, int width, int height, int speedx, int speedy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedx = speedx;
        this.speedy = speedy;
        this.isAlive = true;
    }

    public GameElement(){
        this.isAlive = true;
    }
    // 抽象方法，每个类自己实现自己的draw方法
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeedx() {
        return speedx;
    }

    public void setSpeedx(int speedx) {
        this.speedx = speedx;
    }

    public int getSpeedy() {
        return speedy;
    }

    public void setSpeedy(int speedy) {
        this.speedy = speedy;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
