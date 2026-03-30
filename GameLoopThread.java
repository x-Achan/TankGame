package ccy03.TankGame.Tankgame_v2;

import ccy03.TankGame.Tankgame_v2.Element.Bullet;
import ccy03.TankGame.Tankgame_v2.Element.EnemyTank;
import ccy03.TankGame.Tankgame_v2.Element.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameLoopThread extends Thread{

    GameUI gameUI;

    Random random  = new Random();

    public GameLoopThread(GameUI gameUI){
        this.gameUI = gameUI;
    }

    @Override
    public void run() {
        // 稍微睡一会儿，等 GameUI 窗口完全显示出来再获取 Graphics，否则可能获取到 null
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Graphics g = gameUI.getGraphics();

        //创建内存画像（画板）
        BufferedImage bufferedImage = new BufferedImage(
                GameConfig.UI_WIDTH,
                GameConfig.UI_HEIGHT,
                BufferedImage.TYPE_INT_ARGB
        );
        //获取内存画像的画笔
        Graphics bg = bufferedImage.getGraphics();

        // 遍历地图 字符串二位数组 Map 的每一个元素
        for (int row = 0; row < GameConfig.MAP_1.length; row++) {
            String line = GameConfig.MAP_1[row];
            for (int col = 0; col < line.length(); col++) {
                if(line.charAt(col) == '1'){

                    //计算墙体生成的位置
                    int wallX = col * GameConfig.WALL_SIZE;
                    int wallY = row * GameConfig.WALL_SIZE;
                    Wall w = new Wall(wallX,wallY);

                    //将墙体加入到墙体列表中
                    gameUI.wallList.add(w);

                    //绘制敌军坦克
                }else if(line.charAt(col) == '*'){

                    int tankX = col * GameConfig.TANK_SIZE;
                    int tankY = row * GameConfig.TANK_SIZE;

                    EnemyTank enemyTank = new EnemyTank(tankX,tankY,Direction.DOWN);
                    gameUI.enemyTankList.add(enemyTank);
                }
            }
        }


        while(true){

            // 必须先清屏，用白色覆盖整个窗口 (不然坦克走过会留下残影)，用白板覆盖掉原来的残影
            bg.setColor(Color.BLACK);
            bg.fillRect(0,0, GameConfig.UI_WIDTH, GameConfig.UI_HEIGHT);

            /*
            一边画白板，一边绘制坦克，眼睛会看到 只有白板 或者 有坦克 的画面交替出现
             */

            //  绘制坦克，移动坦克
            gameUI.playerTank.move();


            // 遍历所有墙体wall，判断是否和坦克发生碰撞
            for (int i = 0; i < gameUI.wallList.size(); i++) {
                // intersects() 方法检测两个矩形是否重叠
                if(gameUI.wallList.get(i).getRect().intersects(gameUI.playerTank.getRect())){

                    System.out.println("发生碰撞了");
                    //顶牛：碰撞了，将刚刚加上的速度减回去(退回到碰撞前的坐标)
                    gameUI.playerTank.setX(gameUI.playerTank.getX() - gameUI.playerTank.getSpeedx());
                    gameUI.playerTank.setY(gameUI.playerTank.getY() - gameUI.playerTank.getSpeedy());
                }
            }

            //循环判断每个墙体是否和子弹碰撞
            for (int i = 0; i < gameUI.wallList.size(); i++) {
                for (int j = 0; j < gameUI.bulletsLsit.size(); j++) {
                    Bullet bullet = gameUI.bulletsLsit.get(j);
                    if(gameUI.wallList.get(i).getRect().intersects(bullet.getRect())){
                        System.out.println("子弹和墙体发生了碰撞！");
                        bullet.setAlive(false);
                    }
                }
            }

            //循环判断每个墙体是否和敌方坦克碰撞
            for (int i = 0; i < gameUI.wallList.size(); i++) {
                for (int j = 0; j < gameUI.enemyTankList.size(); j++){
                    EnemyTank et = gameUI.enemyTankList.get(j);
                    if(gameUI.wallList.get(i).getRect().intersects(et.getRect())){
                        System.out.println("敌军坦克撞墙");

                        // 先让坦克回到安全位置，再改变方向
                        et.setX(et.getX() - et.getSpeedx());
                        et.setY(et.getY() - et.getSpeedy());

                        et.changeDirection();
                    }
                }
            }



            // 绘制玩家的子弹
            for (int i = 0; i < gameUI.bulletsLsit.size(); i++) {
                gameUI.bulletsLsit.get(i).draw(bg);
                gameUI.bulletsLsit.get(i).move();
            }

            //移除已经出界的子弹
            for (int i = gameUI.bulletsLsit.size() - 1; i >= 0; i--) {
                if(!gameUI.bulletsLsit.get(i).isAlive()){
                    gameUI.bulletsLsit.remove(i);
                }
            }

            // 绘制墙体
            for (int i = 0; i < gameUI.wallList.size(); i++) {
                gameUI.wallList.get(i).draw(bg);
            }

            //绘制敌人坦克
            for (int i = 0; i < gameUI.enemyTankList.size(); i++) {
                //取出每一个坦克的子弹列表，然后移动和绘制子弹
                //每移动一步，只有2%的概率开火
                if(random.nextInt(100) < 2){
                    gameUI.enemyTankList.get(i).fire();
                }
                gameUI.enemyTankList.get(i).move();
                gameUI.enemyTankList.get(i).draw(bg);
            }

            // 绘制敌军坦克的子弹
            for (int i = 0; i < gameUI.enemyTankList.size(); i++) {
                //取出每一个坦克的子弹列表，然后移动和绘制子弹
                for (int j = 0; j < gameUI.enemyTankList.get(i).bulletList.size(); j++) {
                    Bullet b = gameUI.enemyTankList.get(i).bulletList.get(j);
                    b.move();
                    b.draw(bg);
                }
            }


            //绘制玩家坦克
            gameUI.playerTank.draw(bg);
            //将内存画像直接画在画板上
            g.drawImage(bufferedImage,0,0,null);

             // 线程休眠 控制游戏帧率（大约每秒30帧）
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
