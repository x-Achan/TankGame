package ccy03.TankGame.Tankgame_v2.Element;

import ccy03.TankGame.Tankgame_v2.Direction;
import ccy03.TankGame.Tankgame_v2.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class EnemyTank extends Tank{


    private static final Image upImage;
    private static final Image downImage;
    private static final Image rightImage;
    private static final Image leftImage;
    
    Random random = new Random();

    public ArrayList<Bullet> bulletList = new ArrayList<>();

    //  静态代码块，这个类加载时只执行一次
    // 静态资源初始化
    static {
        try {
            upImage = ImageIO.read(new File("D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\tank\\enemy1U.gif"));
            downImage = ImageIO.read(new File("D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\tank\\enemy1D.gif"));
            rightImage = ImageIO.read(new File("D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\tank\\enemy1R.gif"));
            leftImage = ImageIO.read(new File("D:\\JavaProjects\\pro26\\src\\ccy03\\TankGame\\Tankgame_v2\\img\\tank\\enemy1L.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EnemyTank(int x,int y,Direction dir) {
        super();
        this.x = x;
        this.y = y;
        this.speedx = GameConfig.ENEMY_SPEED;
        this.speedy = GameConfig.ENEMY_SPEED;
        this.dir = dir; //默认朝上
    }

    public EnemyTank(){
        super();
    }


    @Override
    public void draw(Graphics g) {
        if(this.blood <= 0) return;

        //根据不同的方向，画出不同的图像
        if(dir == Direction.UP) g.drawImage(upImage,x,y,width,height,null);
        if(dir == Direction.DOWN) g.drawImage(downImage,x,y,width,height,null);
        if(dir == Direction.RIGHT) g.drawImage(rightImage,x,y,width,height,null);
        if(dir == Direction.LEFT) g.drawImage(leftImage,x,y,width,height,null);

    }

    @Override
    public void move() {
        if(blood == 0) return;

        // 屏幕边界检测，顶牛效果
        if(x<0){
            x=0;
            changeDirection();
        }
        if(y<0){
            y=0;
            changeDirection();
        }
        if(x> GameConfig.UI_WIDTH - GameConfig.TANK_SIZE){
            x = GameConfig.UI_WIDTH - GameConfig.TANK_SIZE;
            changeDirection();
        }
        if(y>GameConfig.UI_HEIGHT - GameConfig.TANK_SIZE){
            y = GameConfig.UI_HEIGHT - GameConfig.TANK_SIZE;
            changeDirection();
        }

        
        x += speedx;
        y += speedy;

    }

    public void fire(){
            Bullet bullet = new Bullet(this);
            bulletList.add(bullet);
    }

    // 改变方向：随机枚举一个方向，不同的方向有不同的speedx和speedy用来控制移动方向
    public void changeDirection(){
        Direction[] dirs = Direction.values(); // 返回所有枚举常量的数组
        Direction newDir = dirs[random.nextInt(dirs.length)];  //从数组中取出一个变量

        this.dir = newDir;
        if(dir == Direction.UP){
            speedx = 0;
            speedy = -GameConfig.ENEMY_SPEED;
        }else if(dir == Direction.DOWN){
            speedx = 0;
            speedy = GameConfig.ENEMY_SPEED;
        }else if(dir == Direction.LEFT){
            speedx = -GameConfig.ENEMY_SPEED;
            speedy = 0;
        }else if(dir == Direction.RIGHT){
            speedx = GameConfig.ENEMY_SPEED;
            speedy = 0;
        }
    }

}
