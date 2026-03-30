package ccy03.TankGame.Tankgame_v2;

import ccy03.TankGame.Tankgame_v2.Element.Bullet;
import ccy03.TankGame.Tankgame_v2.Element.EnemyTank;
import ccy03.TankGame.Tankgame_v2.Element.PlayerTank;
import ccy03.TankGame.Tankgame_v2.Element.Wall;

import javax.swing.*;
import java.util.ArrayList;

public class GameUI extends JFrame {

    GameListener gameListener = new GameListener(this);

    public PlayerTank playerTank = new PlayerTank();

    public ArrayList<Bullet> bulletsLsit = new ArrayList<>();
    public ArrayList<Wall> wallList = new ArrayList<>();
    public ArrayList<EnemyTank> enemyTankList = new ArrayList<>();

    //构造方法
    public GameUI(){
        setTitle("Game");
        setSize(GameConfig.UI_WIDTH, GameConfig.UI_HEIGHT);
        setLocationRelativeTo(null); //窗口在屏幕居中
        setDefaultCloseOperation(EXIT_ON_CLOSE); //关闭窗口，主窗口，程序结束

        addKeyListener(gameListener);

        setVisible(true);

    }

}
