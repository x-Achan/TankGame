package ccy03.TankGame.Tankgame_v1;

import ccy03.TankGame.Tankgame_v1.Element.Bullet;
import ccy03.TankGame.Tankgame_v1.Element.PlayerTank;

import javax.swing.*;
import java.util.ArrayList;

public class GameUI extends JFrame {

    GameListener gameListener = new GameListener(this);
    public PlayerTank playerTank = new PlayerTank();
    public ArrayList<Bullet> bulletsLsit = new ArrayList<>();

    //构造方法
    public GameUI(){
        setTitle("Game");
        setSize(GameConfig.UI_WIDTH,GameConfig.UI_HEIGHT);
        setLocationRelativeTo(null); //窗口在屏幕居中
        setDefaultCloseOperation(EXIT_ON_CLOSE); //关闭窗口，主窗口，程序结束

        addKeyListener(gameListener);

        setVisible(true);

    }

}
