package ccy03.TankGame.Tankgame_v1.Element;

import ccy03.TankGame.Tankgame_v1.Element.GameElement;
import ccy03.TankGame.Tankgame_v1.Element.PlayerTank;
import ccy03.TankGame.Tankgame_v1.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameElement {

    private static final Image image;

    PlayerTank owner;

    static {
        String path = "D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v1\\img\\fire\\1.png";
        File file = new File(path);

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Bullet(PlayerTank owner){
        super();
        this.owner = owner;
        this.x = owner.getX();
        this.y = owner.getY();
        this.width = 20;
        this.height = 20;
        this.speedx = 0;
        this.speedy = -20;
    }

    public void draw(Graphics g){
        if(x<0 || x> GameConfig.UI_WIDTH || y < 0 || y > GameConfig.UI_HEIGHT) return;
        g.drawImage(image,x+20,y,width,height,null);

    }

    public void move(){
        if(x<0 || x>500 || y<0 || y>800) {
            this.isAlive = false;
            return;
        }
        y += speedy;
    }

}
