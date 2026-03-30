package ccy03.TankGame.Tankgame_v2.Element;

import ccy03.TankGame.Tankgame_v2.Direction;
import ccy03.TankGame.Tankgame_v2.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameElement {

    private static final Image image;

    private final Tank owner;

    protected Direction dir;

    static {
        String path = "D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\fire\\1.png";
        File file = new File(path);

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Bullet(Tank owner){
        super();
        this.owner = owner;
     //在创建的那一刻，就已经将owner的坐标值取了出来

        this.width = GameConfig.BULLET_SIZE;
        this.height = GameConfig.BULLET_SIZE;

        //获取 owner的初始方向，将初始方向保存
        this.dir = owner.getDir();


        //子弹的 移动方向 和 发射位置 在出生的时候就已经决定了  (在创建的那一刻，就已经将owner的坐标值取了出来)
        if(dir == Direction.UP){
            this.x = owner.getX();
            this.y = owner.getY() - this.height;
            this.speedx = 0;
            this.speedy = -GameConfig.BULLET_SPEED;
        }else if(dir == Direction.DOWN){
            this.x = owner.getX();
            this.y = owner.getY() + this.height;
            this.speedx = 0;
            this.speedy = GameConfig.BULLET_SPEED;
        }else if(dir == Direction.LEFT){
            this.x = owner.getX() - this.width;
            this.y = owner.getY();
            this.speedx = -GameConfig.BULLET_SPEED;
            this.speedy = 0;
        }else if(dir == Direction.RIGHT){
            this.x = owner.getX() + this.width;
            this.y = owner.getY();
            this.speedx = GameConfig.BULLET_SPEED;
            this.speedy = 0;
        }
    }

    public void draw(Graphics g){
        if(x<0 || x> GameConfig.UI_WIDTH || y < 0 || y > GameConfig.UI_HEIGHT) {
            this.isAlive = false;
            return;
        }

        g.drawImage(image,x,y,width,height,null);

    }

    // 子弹的移动主要有三种
    public void move(){
        if(x<0 || x>GameConfig.UI_WIDTH || y<0 || y>GameConfig.UI_HEIGHT) {
            this.isAlive = false;
            return;
        }

        y += this.speedy;
        x += this.speedx;
    }

}
