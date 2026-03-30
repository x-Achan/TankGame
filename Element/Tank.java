package ccy03.TankGame.Tankgame_v2.Element;
import ccy03.TankGame.Tankgame_v2.Direction;
import ccy03.TankGame.Tankgame_v2.GameConfig;

import java.awt.*;

import static ccy03.TankGame.Tankgame_v2.Direction.UP;

public abstract class Tank extends GameElement {

    protected int blood;

    // 坦克的朝向
    protected Direction dir;


    public Tank() {
        super();
        this.blood = 100;
        this.width = GameConfig.TANK_SIZE;
        this.height = GameConfig.TANK_SIZE;
    }

    public abstract void move();

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
