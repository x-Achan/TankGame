package ccy03.TankGame.Tankgame_v1;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLoopThread extends Thread{

    GameUI gameUI;

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

        while(true){

            // 必须先清屏，用白色覆盖整个窗口 (不然坦克走过会留下残影)，用白板覆盖掉原来的残影
            bg.setColor(Color.WHITE);
            bg.fillRect(0,0,GameConfig.UI_WIDTH,GameConfig.UI_HEIGHT);

            /*
            一边画白板，一边绘制坦克，眼睛会看到 只有白板 或者 有坦克 的画面交替出现
             */

            //  绘制坦克，移动坦克
            gameUI.playerTank.draw(bg);
            gameUI.playerTank.move();

            // 绘制玩家的子弹
            for (int i = 0; i < gameUI.bulletsLsit.size(); i++) {
                gameUI.bulletsLsit.get(i).draw(bg);
                gameUI.bulletsLsit.get(i).move();
            }

            //移除已经出界的子弹
            for (int i = 0; i < gameUI.bulletsLsit.size(); i++) {
                if(!gameUI.bulletsLsit.get(i).isAlive()){
                    gameUI.bulletsLsit.remove(i);
                }
            }

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
