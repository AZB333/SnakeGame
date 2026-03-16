package SnakeGame;

import java.util.Random;
import java.util.TimerTask;

public class Apple extends TimerTask implements IRectangle {


    private int posx;
    private int posy;
    private Snake snake;


    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosx(int increment) { this.posx = this.posx + increment; }

    public void setPosy(int increment) { this.posy =  this.posy + increment; }


    public Apple(Snake snake) {
        this.snake = snake;
    }
    public Apple(Snake snake, int posx, int posy){
        this.snake = snake;
        this.posx = posx;
        this.posy = posy;
    }

    public Apple() {
        this.posx = 25 * new Random().nextInt(20);
        this.posy = 25 * new Random().nextInt(20);
    }

    @Override
    public void run() {
        if (this.snake.getApple() == null) {
            this.snake.setApple(new Apple());
        }
    }
}