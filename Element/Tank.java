package ccy03.TankGame.Tankgame_v1.Element;

public abstract class Tank extends GameElement {

    protected int blood;

    public Tank() {
        super();
        this.blood = 100;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
}
