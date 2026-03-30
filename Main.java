package ccy03.TankGame.Tankgame_v1;

//主线程
public class Main {
    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        GameLoopThread gameLoopThread = new GameLoopThread(gameUI);
        gameLoopThread.start();
    }
}
