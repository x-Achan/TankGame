package ccy03.TankGame.Tankgame_v2.Element;

import ccy03.TankGame.Tankgame_v2.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Wall extends GameElement{
    static final Image image;

    static {
        try {
            image = ImageIO.read(new File("D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\wall\\walls.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Wall(int x,int y){
        super();
        this.x = x;
        this.y = y;
        this.width = GameConfig.WALL_SIZE;
        this.height = GameConfig.WALL_SIZE;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,x,y, GameConfig.WALL_SIZE,GameConfig.WALL_SIZE,null);
    }
}
