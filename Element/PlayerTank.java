package ccy03.TankGame.Tankgame_v2.Element;

import ccy03.TankGame.Tankgame_v2.Direction;
import ccy03.TankGame.Tankgame_v2.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class PlayerTank extends Tank {
    // 玩家坦克的图片，static静态变量，整个类只需要保存一份即可，如果不是静态资源，每个实例都会创建一个image，浪费内存
    private static final Image upImage;
    private static final Image downImage;
    private static final Image rightImage;
    private static final Image leftImage;

    //  静态代码块，这个类加载时只执行一次
    // 静态资源初始化
    static {
        try {

            upImage = ImageIO.read(new File(GameConfig.PlAYERTANK_IMAGE_UP_PATH));
            downImage = ImageIO.read(new File(GameConfig.PlAYERTANK_IMAGE_DOWN_PATH));
            rightImage = ImageIO.read(new File(GameConfig.PlAYERTANK_IMAGE_RIGHT_PATH));
            leftImage = ImageIO.read(new File(GameConfig.PlAYERTANK_IMAGE_LEFT_PATH));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerTank() {
        super();
        this.speedy = 0;
        this.speedx = 0;

        this.x = 380;
        this.y = 530;

        this.dir = Direction.UP; //默认朝上
    }

    // 将image图片画在窗口上（x,y）
    public void draw(Graphics g){
        if(this.blood <= 0) return;

        if(dir == Direction.UP) g.drawImage(upImage,x,y,width,height,null);
        if(dir == Direction.DOWN) g.drawImage(downImage,x,y,width,height,null);
        if(dir == Direction.RIGHT) g.drawImage(rightImage,x,y,width,height,null);
        if(dir == Direction.LEFT) g.drawImage(leftImage,x,y,width,height,null);

    }

    //更新 x 和 y 的坐标
    public void move(){
        if(blood <= 0) return;

        if(x<0) x=0;
        if(y<0) y=0;
        if(x>GameConfig.UI_WIDTH - GameConfig.TANK_SIZE) x = GameConfig.UI_WIDTH - GameConfig.TANK_SIZE;
        if(y>GameConfig.UI_HEIGHT - GameConfig.TANK_SIZE) y = GameConfig.UI_HEIGHT - GameConfig.TANK_SIZE;

        x+=speedx;
        y+=speedy;
    }


}
