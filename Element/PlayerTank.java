package ccy03.TankGame.Tankgame_v1.Element;

import ccy03.TankGame.Tankgame_v1.Element.Tank;
import ccy03.TankGame.Tankgame_v1.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlayerTank extends Tank {
    // 玩家坦克的图片，static静态变量，整个类只需要保存一份即可，如果不是静态资源，每个实例都会创建一个image，浪费内存
    private static final Image image;

    //  静态代码块，这个类加载时只执行一次
    // 静态资源初始化
    static {
        String path = "D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v1\\img\\tank\\p2tankU.gif";
        File file = new File(path);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerTank() {
        super();
        this.speedy = 0;
        this.speedx = 0;
        this.height = 50;
        this.width = 50;
        this.y = 700;
        this.x = 250;
    }

    // 将image图片画在窗口上（x,y）
    public void draw(Graphics g){
        if(this.blood <= 0) return;
        g.drawImage(image,x,y,width,height,null);
    }

    //更新 x 和 y 的坐标
    public void move(){
        if(blood <= 0) return;

        if(x < 0 || x> GameConfig.UI_WIDTH - width || y < 0 || y>GameConfig.UI_HEIGHT - height) {
            x = 250;
            y = 700;
        }else{
            x+= speedx;
            y+= speedy;
        }

    }


}
