package ccy03.TankGame.Tankgame_v1;
import ccy03.TankGame.Tankgame_v1.Element.Bullet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener {

    GameUI gameUI;

    public GameListener(GameUI gameUI){

        this.gameUI = gameUI;

    }


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("键盘键入");
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下");

        int keyCode = e.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_W: gameUI.playerTank.setSpeedy(-10); break;
            case KeyEvent.VK_S: gameUI.playerTank.setSpeedy(10); break;
            case KeyEvent.VK_A: gameUI.playerTank.setSpeedx(-10);break;
            case KeyEvent.VK_D: gameUI.playerTank.setSpeedx(10); break;

            case KeyEvent.VK_J: gameUI.bulletsLsit.add(new Bullet(gameUI.playerTank));
        }

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");

        gameUI.playerTank.setSpeedx(0);
        gameUI.playerTank.setSpeedy(0);

    }
}
