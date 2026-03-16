package SnakeGame.rectangles;

import SnakeGame.IRectangle;

import java.util.Random;
import java.util.TimerTask;

public class Apple extends Rectangle implements IRectangle {


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
        super(25 * new Random().nextInt(20), 25 * new Random().nextInt(20));
        this.snake = snake;
    }

}